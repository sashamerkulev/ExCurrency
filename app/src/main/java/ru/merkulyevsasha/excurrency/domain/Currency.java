package ru.merkulyevsasha.excurrency.domain;

public class Currency {

    private final String numCode;
    private final String chrCode;
    private final int nomianal;
    private final String name;
    private final double value;

    public Currency(String numCode, String chrCode, int nomianal, String name, double value) {
        this.numCode = numCode;
        this.chrCode = chrCode;
        this.nomianal = nomianal;
        this.name = name;
        this.value = value;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getChrCode() {
        return chrCode;
    }

    public int getNomianal() {
        return nomianal;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
