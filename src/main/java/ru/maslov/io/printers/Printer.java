package ru.maslov.io.printers;

import ru.maslov.entities.ResultRecord;

import java.io.IOException;
import java.io.Writer;

/**
 * Интерфейс сохранения информации о записях, попавших в join, в файл
 */
public interface Printer {
    /**
     * Метод, осуществляющий запись заголовка результата join-вычислений
     * @param w writer, в который проиводится запись
     */
    void initPrint(Writer w) throws IOException;

    /**
     * Метод, осуществляющий запись результата join-вычислений
     * @param record зписываемая запись
     * @param w writer, в который проиводится запись
     */
    void print (ResultRecord record, Writer w) throws IOException;
}
