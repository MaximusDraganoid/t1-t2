package ru.maslov.io.printers.impl;

import ru.maslov.entities.ResultRecord;
import ru.maslov.io.printers.Printer;

import java.io.IOException;
import java.io.Writer;

/**
 * Форматированный вывод результатов join-вычислений в writer
 */
public class FormatPrinterImpl implements Printer {

    private static final String FORMAT_OUTPUT = "%-25d %-25.100s %-25.100s"
            + System.getProperty("line.separator");

    @Override
    public void initPrint(Writer w) throws IOException {
        w.write(String.format("%-25s %-25s %-25s" + System.getProperty("line.separator"),
                "ID", "A.VALUE", "B.VALUE"));
    }

    @Override
    public void print(ResultRecord record, Writer w) throws IOException {
        w.write(String.format(FORMAT_OUTPUT,
                record.getRecordId(),
                record.getValueFromA(),
                record.getValueFromB()));
    }
}
