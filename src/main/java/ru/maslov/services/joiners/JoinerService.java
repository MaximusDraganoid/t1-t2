package ru.maslov.services.joiners;

import ru.maslov.io.printers.Printer;
import ru.maslov.io.printers.impl.FormatPrinterImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JoinerService {

    private static final int BUFFER_SIZE = 512;
    public static final Printer printer = new FormatPrinterImpl();

    public static <T> void innerJoin(T leftTable,
                                     T rightTable,
                                     String outputFileName,
                                     Joiner<T> joiner) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(
                new FileWriter(outputFileName), BUFFER_SIZE)) {
            if (joiner.getPrinter() == null || joiner.getWriter() == null) {
                joiner.setPrinter(printer);
                joiner.setWriter(bufferedWriter);
            }
            joiner.join(leftTable, rightTable);
        } catch (IOException e) {
            throw new RuntimeException("Возникли проблемы при записи " +
                    "реультатов в файл во время работы с " + leftTable.getClass().getName(), e);
        }
    }
}
