package ru.maslov.services.joiners;

import ru.maslov.io.printers.Printer;

import java.io.IOException;
import java.io.Writer;

public interface Joiner {
    void join(Printer printer, Writer writer) throws IOException;
}
