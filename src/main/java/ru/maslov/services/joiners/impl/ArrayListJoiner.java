package ru.maslov.services.joiners.impl;

import ru.maslov.entities.Record;
import ru.maslov.entities.ResultRecord;
import ru.maslov.io.printers.Printer;
import ru.maslov.services.joiners.Joiner;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class ArrayListJoiner extends Joiner<ArrayList<Record>> {

    public ArrayListJoiner() {}

    public ArrayListJoiner(Printer printer, Writer writer) {
        super(printer, writer);
    }

    @Override
    public void join(ArrayList<Record> leftTable, ArrayList<Record> rightTable) throws IOException {
        for (Record recordFromA : leftTable) {
            for (Record recordFromB : rightTable) {
                if (recordFromA.getId() == recordFromB.getId()) {
                    ResultRecord resultRecord = new ResultRecord(recordFromA.getId(),
                            recordFromA.getValue(),
                            recordFromB.getValue());
                    getPrinter().print(resultRecord, getWriter());
                }
            }
        }

    }
}
