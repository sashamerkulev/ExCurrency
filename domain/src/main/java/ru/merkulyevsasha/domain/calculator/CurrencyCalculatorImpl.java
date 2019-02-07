package ru.merkulyevsasha.domain.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyCalculatorImpl implements CurrencyCalculator {
    @Override
    public double calculate(double originalValue, int nominal, double exchangeValue) {
        return round4(originalValue * nominal / exchangeValue);
    }

    @Override
    public double calculateToRubls(double originalValue, int nominal, double exchangeValue) {
        return round4(originalValue * exchangeValue / nominal);
    }

    @Override
    public double calculate(double originalValue, int nominal1, double exchangeValue1, int nominal2, double exchangeValue2) {
        double result = originalValue * exchangeValue1 / nominal1;
        return round4(calculate(result, nominal2, exchangeValue2));
    }

    private double round4(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
