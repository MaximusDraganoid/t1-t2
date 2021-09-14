package ru.maslov.io.scanners.impl;

import ru.maslov.entities.Record;
import ru.maslov.exceptions.RecordValidationException;
import ru.maslov.io.scanners.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileScanner implements Scanner {

    private static final int START_ARRAY_CAPACITY = 8192;
    private static final int MAX_STRING_LEN = 100;


    private String fileName;

    public FileScanner(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<Record> scanRecords() {
        ArrayList<Record> resultList = new ArrayList<>(START_ARRAY_CAPACITY);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int row = 1;
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                try {
                    recordDataValidation(lineData);
                    resultList.add(new Record(Long.parseLong(lineData[0]), lineData[1]));
                } catch (RecordValidationException e) {
                    System.out.println("Ошибка входных данных, строка " + row + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли прблемы при считывании данных из входного файла." +
                    " Убедитесь, что программе переданы корректное имя входного файла", e);
        }

        return resultList;
    }

    private void recordDataValidation(String[] recordData) {
        if (recordData.length != 2) {
            throw new RecordValidationException("В строке есть лишние данные или данных не хватает");
        }

        for (int i = 0; i < recordData.length; i++) {
            recordData[i] = recordData[i].trim();
        }

        if (recordData[0].length() == 0 || recordData[1].length() == 0) {
            throw new RecordValidationException("Не задано id или значение");
        }

        if (recordData[1].length() > MAX_STRING_LEN) {
            recordData[1] = recordData[1].substring(0, MAX_STRING_LEN);
        }

        long l;
        try {
            l = Long.parseLong(recordData[0]);
        } catch (NumberFormatException e) {
            throw new RecordValidationException("id задано в неверном формате", e);
        }

        if (l < 0) {
            throw new RecordValidationException("id задано негативным значением");
        }

    }
}
