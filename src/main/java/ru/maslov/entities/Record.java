package ru.maslov.entities;

/**
 * Запись из файла
 */
public class Record {
    private long id;
    private String value;

    public Record (long id, String value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
