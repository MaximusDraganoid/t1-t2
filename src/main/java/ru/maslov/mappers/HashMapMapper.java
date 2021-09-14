package ru.maslov.mappers;

import ru.maslov.entities.Record;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HashMapMapper {

    public HashMap<Long, List<String>> map(List<Record> records) {
        HashMap<Long, List<String>> map = new HashMap<>();
        for (Record record : records) {
            map.putIfAbsent(record.getId(), new LinkedList<>());
            map.get(record.getId()).add(record.getValue());
        }
        return map;
    }
}
