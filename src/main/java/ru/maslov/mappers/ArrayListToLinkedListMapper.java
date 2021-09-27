package ru.maslov.mappers;

import ru.maslov.entities.Record;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ArrayListToLinkedListMapper {
    public static LinkedList<Record> map(ArrayList<Record> list) {
        LinkedList<Record> result = new LinkedList<>(list);
        result.sort(Comparator.comparingLong(Record::getId));
        return result;
    }
}
