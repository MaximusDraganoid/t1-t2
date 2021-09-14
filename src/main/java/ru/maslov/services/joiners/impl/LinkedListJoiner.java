package ru.maslov.services.joiners.impl;

import ru.maslov.entities.Record;
import ru.maslov.entities.ResultRecord;
import ru.maslov.io.printers.Printer;
import ru.maslov.services.joiners.Joiner;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListJoiner implements Joiner {
    private LinkedList<Record> leftTable;
    private LinkedList<Record> rightTable;

    public LinkedListJoiner(LinkedList<Record> leftTable, LinkedList<Record> rightTable) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
    }

    @Override
    public void join(Printer printer, Writer writer) throws IOException {
        int aStartPointer = 0;
        int bStartPointer = 0;
        int bEndPointer = 0;
        long prevAid = leftTable.get(aStartPointer).getId();

        while (aStartPointer < leftTable.size() && bStartPointer < rightTable.size()) {
            if (leftTable.get(aStartPointer).getId() < rightTable.get(bStartPointer).getId()) {
                aStartPointer++;
                continue;
            }

            if (leftTable.get(aStartPointer).getId() > rightTable.get(bStartPointer).getId()) {
                bStartPointer++;
                continue;
            }

            if (prevAid != leftTable.get(aStartPointer).getId()) {
                prevAid = leftTable.get(aStartPointer).getId();
                bEndPointer = findNextWithOtherId(rightTable, bStartPointer, leftTable.get(aStartPointer).getId());

            }

            for (int i = bStartPointer; i < bEndPointer; i++) {
                ResultRecord record = new ResultRecord(leftTable.get(aStartPointer).getId(),
                        leftTable.get(aStartPointer).getValue(),
                        rightTable.get(i).getValue());
                printer.print(record, writer);
            }

            aStartPointer++;

        }
    }

    private int findNextWithOtherId(List<Record> list, int startInd, long currentId) {
        int resultInd = startInd + 1;
        Iterator<Record> iterator = list.listIterator(resultInd);
        while (iterator.hasNext()) {
            if (iterator.next().getId() != currentId) {
                break;
            } else {
                resultInd++;
            }
        }
        return resultInd;
    }
}
