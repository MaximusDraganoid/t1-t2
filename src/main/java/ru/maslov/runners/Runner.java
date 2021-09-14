package ru.maslov.runners;

import ru.maslov.entities.Record;
import ru.maslov.io.printers.impl.FormatPrinterImpl;
import ru.maslov.io.printers.Printer;
import ru.maslov.io.scanners.impl.FileScanner;
import ru.maslov.io.scanners.Scanner;
import ru.maslov.mappers.HashMapMapper;
import ru.maslov.services.FileInnerJoinServiceImpl;
import ru.maslov.services.joiners.impl.ArrayListJoiner;
import ru.maslov.services.joiners.impl.HashMapJoiner;
import ru.maslov.services.joiners.impl.LinkedListJoiner;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Runner {
    private static final String ARRAY_LIST_OUTPUT_NAME = "src"
            + File.separator +
            "main" +
            File.separator +
            "resources" +
            File.separator +
            "array_list.txt";
    private static final String LINKED_LIST_OUTPUT_NAME = "src"
            + File.separator +
            "main" +
            File.separator +
            "resources" +
            File.separator +
            "linked_list.txt";
    private static final String HASH_MAP_OUTPUT_NAME = "src"
            + File.separator +
            "main" +
            File.separator +
            "resources" +
            File.separator +
            "hash_map.txt";

    public static void main(String[] args) {
        validationInputParameters(args);

        //todo: валидация данных
        //todo: получение параметров изкомандной строки

        System.out.println("Считывание данных и инициализация значений, необходимых для вычилений...");
        Scanner scannerForA = new FileScanner(args[0]);
        ArrayList<Record> recordsA = scannerForA.scanRecords();

        Scanner scannerForB = new FileScanner(args[1]);
        ArrayList<Record> recordsB = scannerForB.scanRecords();

        ArrayListJoiner arrayListJoiner = new ArrayListJoiner(recordsA, recordsB);

        LinkedList<Record> aLink = new LinkedList<>(recordsA);
        aLink.sort(Comparator.comparingLong(Record::getId));

        LinkedList<Record> bLink = new LinkedList<>(recordsB);
        bLink.sort(Comparator.comparingLong(Record::getId));

        LinkedListJoiner linkedListJoiner = new LinkedListJoiner(aLink, bLink);

        HashMapMapper mapper = new HashMapMapper();
        Map<Long, List<String>> mapForA = mapper.map(aLink);
        Map<Long, List<String>> mapForB = mapper.map(bLink);

        HashMapJoiner hashMapJoiner = new HashMapJoiner(mapForA, mapForB);

        Printer printer = new FormatPrinterImpl();
        FileInnerJoinServiceImpl joinService = new FileInnerJoinServiceImpl(printer);

        System.out.println("Приступаем к вычислениям...");
        System.out.println("Проводим вычисления для ArrayList");
        joinService.innerJoin(arrayListJoiner, ARRAY_LIST_OUTPUT_NAME);

        System.out.println("Вычисления окончены! "
                + System.getProperty("line.separator")
                + "Проводим вычисления для LinkedList");
        joinService.innerJoin(linkedListJoiner, LINKED_LIST_OUTPUT_NAME);

        System.out.println("Вычисления окончены!"
                + System.getProperty("line.separator")
                + "Проводим вычисления для HashMap");
        joinService.innerJoin(hashMapJoiner, HASH_MAP_OUTPUT_NAME);

        System.out.println("Вычисления окончены! Ознакомьтесь с результатами вычислений");
    }

    private static void validationInputParameters(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Передано слишком много параметров");
        }

        existAndTrueFileCheck(args[0]);
        existAndTrueFileCheck(args[1]);

    }

    private static void existAndTrueFileCheck(String fileName) {
        File aFile = new File(fileName);
        try {
            if (!aFile.exists()) {
                aFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли приблемы при создани файла для записи ошибок", e);
        }
        if (!aFile.isFile()) {
            throw new RuntimeException("Указанный файл для вывода ошибок не является файлом!");
        }
    }

}
