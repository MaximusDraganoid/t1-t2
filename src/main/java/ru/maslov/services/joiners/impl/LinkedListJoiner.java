package ru.maslov.services.joiners.impl;

import ru.maslov.entities.Record;
import ru.maslov.entities.ResultRecord;
import ru.maslov.io.printers.Printer;
import ru.maslov.services.joiners.Joiner;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListJoiner extends Joiner<LinkedList<Record>> {
    private LinkedList<Record> leftTable;
    private LinkedList<Record> rightTable;

    public LinkedListJoiner() {}

    public LinkedListJoiner(Printer printer, Writer writer) {
        super(printer, writer);
    }

    @Override
    public void join(LinkedList<Record> leftTable, LinkedList<Record> rightTable) throws IOException {
        ListIterator<Record> leftIterator = leftTable.listIterator();
        ListIterator<Record> rightIterator = rightTable.listIterator();

        if (!leftIterator.hasNext() || !rightIterator.hasNext()) {
            return;
        }
        int stepsBack = 1;
        Record left, right;

        while (leftIterator.hasNext()) {
            left = leftIterator.next();
            while (rightIterator.hasNext()) {
                right = rightIterator.next();
                if (left.getId() < right.getId()) {
                    stepBack(rightIterator, stepsBack);
                    stepsBack = 1;
                    break;
                } else if (left.getId() == right.getId()) {
                    getPrinter().print(
                            new ResultRecord(left.getId(),
                                    left.getValue(),
                                    right.getValue()),
                            getWriter()
                    );
                    stepsBack++;
                }
            }
            stepBack(rightIterator, stepsBack);
            stepsBack = 1;
        }
    }

    private void stepBack(ListIterator<Record> iterator, int stepsBack) {
        for (int i = 0; i < stepsBack; i++) {
            if (iterator.hasPrevious()) {
                iterator.previous();
            } else {
                break;
            }
        }
    }
}
