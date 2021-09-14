package ru.maslov.services.joiners.impl;

import ru.maslov.entities.Record;
import ru.maslov.entities.ResultRecord;
import ru.maslov.io.printers.Printer;
import ru.maslov.services.joiners.Joiner;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class ArrayListJoiner implements Joiner {
    private ArrayList<Record> leftTable;
    private ArrayList<Record> rightTable;

    public ArrayListJoiner(ArrayList<Record> leftTable, ArrayList<Record> rightTable) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
    }

    @Override
    public void join(Printer printer, Writer writer) throws IOException {
        for (Record recordFromA : leftTable) {
            for (Record recordFromB : rightTable) {
                if (recordFromA.getId() == recordFromB.getId()) {
                    ResultRecord resultRecord = new ResultRecord(recordFromA.getId(),
                            recordFromA.getValue(),
                            recordFromB.getValue());
                    printer.print(resultRecord, writer);
                }
            }
        }

    }
}
