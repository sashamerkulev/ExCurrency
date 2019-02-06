package ru.merkulyevsasha.excurrency.domain.calculator;

public interface CurrencyCalculator {

    double calculate(double originalValue, int nominal, double exchangeValue);

    double calculate(double originalValue, int nominal1, double exchangeValue1, int nominal2, double exchangeValue2);

}
