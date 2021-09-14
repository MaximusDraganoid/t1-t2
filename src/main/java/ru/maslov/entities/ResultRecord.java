package ru.maslov.entities;

public class ResultRecord {
    private long recordId;
    private String valueFromA;
    private String valueFromB;

    public ResultRecord(long recordId, String valueFromA, String valueFromB) {
        this.recordId = recordId;
        this.valueFromA = valueFromA;
        this.valueFromB = valueFromB;
    }

    public long getRecordId() {
        return recordId;
    }

    public String getValueFromA() {
        return valueFromA;
    }

    public String getValueFromB() {
        return valueFromB;
    }
}
