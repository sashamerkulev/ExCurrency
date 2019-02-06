package ru.merkulyevsasha.excurrency.domain.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyCalculatorImpl implements CurrencyCalculator {
    @Override
    public double calculate(double originalValue, int nominal, double exchangeValue) {
        return round(originalValue * nominal / exchangeValue, 4);
    }

    @Override
    public double calculateToRubls(double originalValue, int nominal, double exchangeValue) {
        return round(originalValue * exchangeValue / nominal, 4);
    }

    @Override
    public double calculate(double originalValue, int nominal1, double exchangeValue1, int nominal2, double exchangeValue2) {
        double result = originalValue * exchangeValue1 / nominal1;
        return round(calculate(result, nominal2, exchangeValue2), 4);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
