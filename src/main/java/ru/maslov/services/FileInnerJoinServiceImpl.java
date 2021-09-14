package ru.maslov.services;

import ru.maslov.io.printers.Printer;
import ru.maslov.services.joiners.Joiner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс, который выполняет аналог INNER JOIN между фалйами
 */
public class FileInnerJoinServiceImpl implements InnerJoinService {

    private static final int BUFFER_SIZE = 512;



    /**
     * Принтер, который будет выполнять запись результатов вычисления INNER JOIN-а
     */
    private Printer outPrinter;

    public FileInnerJoinServiceImpl(Printer outPrinter) {
        this.outPrinter = outPrinter;
    }

    @Override
    public void innerJoin(Joiner joiner, String outputFileName) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(outputFileName), BUFFER_SIZE)) {

            outPrinter.initPrint(bufferedWriter);
            joiner.join(outPrinter, bufferedWriter);

        } catch (IOException e) {
            throw new RuntimeException("Возникли проблемы при записи реультатов в файл", e);
        }
    }

}
