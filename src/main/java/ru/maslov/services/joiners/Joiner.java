package ru.maslov.services.joiners;

import ru.maslov.io.printers.Printer;

import java.io.IOException;
import java.io.Writer;

public abstract class Joiner<T> {
    private Printer printer;
    private Writer writer;

    public Joiner() {}

    public Joiner(Printer printer, Writer writer) {
        this.printer = printer;
        this.writer = writer;
    }

    public abstract void join(T table1, T table2) throws IOException;

    public Printer getPrinter() {
        return printer;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
