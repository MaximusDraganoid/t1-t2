package ru.maslov.services.joiners.impl;

import ru.maslov.entities.ResultRecord;
import ru.maslov.io.printers.Printer;
import ru.maslov.services.joiners.Joiner;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class HashMapJoiner implements Joiner {

    private Map<Long, List<String>> leftTable;
    private Map<Long, List<String>> rightTable;

    public HashMapJoiner(Map<Long, List<String>> leftTable, Map<Long, List<String>> rightTable) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
    }

    @Override
    public void join(Printer printer, Writer writer) throws IOException {
        for (Map.Entry<Long, List<String>> recordInfo : leftTable.entrySet()) {
            if (rightTable.containsKey(recordInfo.getKey())) {
                printMapResult(printer, writer,
                        recordInfo.getValue(),
                        rightTable.get(recordInfo.getKey()),
                        recordInfo.getKey());
            }
        }
    }

    private void printMapResult(Printer printer, Writer writer,
                                List<String> aList, List<String> bList, Long id)
            throws IOException{
        for (String valueFromA : aList) {
            for (String valueFromB : bList) {
                ResultRecord record = new ResultRecord(id, valueFromA, valueFromB);
                printer.print(record, writer);
            }
        }
    }
}
