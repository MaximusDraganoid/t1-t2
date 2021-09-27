package ru.maslov.runners;

import ru.maslov.entities.Record;
import ru.maslov.io.printers.Printer;
import ru.maslov.io.printers.impl.FormatPrinterImpl;
import ru.maslov.io.scanners.Scanner;
import ru.maslov.io.scanners.impl.FileScanner;
import ru.maslov.mappers.ArrayListToLinkedListMapper;
import ru.maslov.mappers.HashMapMapper;
import ru.maslov.services.joiners.JoinerService;
import ru.maslov.services.joiners.impl.ArrayListJoiner;
import ru.maslov.services.joiners.impl.HashMapJoiner;
import ru.maslov.services.joiners.impl.LinkedListJoiner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Runner {

    private static final String STARTING_CALCULUS_MESSAGE = "Проводим вычисления для ";
    private static final String FINISH_CALCULUS_MESSAGE = System.getProperty("line.separator")
            + "Вычисления окончены!"
            + System.getProperty("line.separator");

    private static final String BASE_PATH = "src"
            + File.separator +
            "main" +
            File.separator +
            "resources" +
            File.separator;

    private static final String ARRAY_LIST_OUTPUT_NAME = "array_list.txt";
    private static final String LINKED_LIST_OUTPUT_NAME = "linked_list.txt";
    private static final String HASH_MAP_OUTPUT_NAME = "hash_map.txt";

    public static void main(String[] args) {
        validationInputParameters(args);

        System.out.println("Считывание данных и инициализация значений, необходимых для вычилений...");
        Scanner scannerForA = new FileScanner(args[0]);
        ArrayList<Record> recordsA = scannerForA.scanRecords();

        Scanner scannerForB = new FileScanner(args[1]);
        ArrayList<Record> recordsB = scannerForB.scanRecords();

        LinkedList<Record> aLink = ArrayListToLinkedListMapper.map(recordsA);
        LinkedList<Record> bLink = ArrayListToLinkedListMapper.map(recordsB);

        Map<Long, List<String>> mapForA = HashMapMapper.map(aLink);
        Map<Long, List<String>> mapForB = HashMapMapper.map(bLink);

        Printer printer = new FormatPrinterImpl();

        System.out.println("Приступаем к вычислениям...");
        System.out.println(STARTING_CALCULUS_MESSAGE + "ArrayList");
        JoinerService.innerJoin(recordsA,
                recordsB,
                BASE_PATH + ARRAY_LIST_OUTPUT_NAME,
                new ArrayListJoiner());

        System.out.println(FINISH_CALCULUS_MESSAGE
                + STARTING_CALCULUS_MESSAGE + "LinkedList");
        JoinerService.innerJoin(aLink,
                bLink,
                BASE_PATH + LINKED_LIST_OUTPUT_NAME,
                new LinkedListJoiner());

        System.out.println(FINISH_CALCULUS_MESSAGE
                + STARTING_CALCULUS_MESSAGE + "HashMap");
        JoinerService.innerJoin(mapForA,
                mapForB,
                BASE_PATH + HASH_MAP_OUTPUT_NAME,
                new HashMapJoiner());

        System.out.println(FINISH_CALCULUS_MESSAGE);
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
