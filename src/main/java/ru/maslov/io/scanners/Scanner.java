package ru.maslov.io.scanners;

import ru.maslov.entities.Record;

import java.util.ArrayList;

public interface Scanner {
    ArrayList<Record> scanRecords();
}
