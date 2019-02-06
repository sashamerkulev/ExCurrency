package ru.merkulyevsasha.excurrency.domain.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyCalculatorImplTest {

    private CurrencyCalculator calculator = new CurrencyCalculatorImpl();

    @Test
    public void calculate_returns_rubl() {
        double result = calculator.calculate(300, 100, 13.4708);
        Assert.assertEquals(2227.0392, result, 0);
    }

    @Test
    public void calculate_returns_pounds() {
        double result = calculator.calculate(300, 1, 47.5179, 1, 85.4693);
        Assert.assertEquals(166.7894, result, 0);
    }

}