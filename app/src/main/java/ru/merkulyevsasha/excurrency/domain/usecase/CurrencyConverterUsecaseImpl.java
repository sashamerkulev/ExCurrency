package ru.merkulyevsasha.excurrency.domain.usecase;

import ru.merkulyevsasha.excurrency.domain.ConvertCurrencyCallback;
import ru.merkulyevsasha.excurrency.domain.calculator.CurrencyCalculator;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

public class CurrencyConverterUsecaseImpl implements CurrencyConverterUsecase {

    private final CurrencyCalculator calculator;

    public CurrencyConverterUsecaseImpl(CurrencyCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void convert(double value, String curFrom, String curTo,
        Currency from, Currency to, ConvertCurrencyCallback callback) {
        if (curFrom.equals("RUB") && curTo.equals("RUB")) callback.onFailure();
        if (curFrom.equals("RUB")) {
            callback.onSuccess(calculator.calculate(value, to.getNomianal(), to.getValue()));
        } else {
            if (curTo.equals("RUB")) {
                callback.onSuccess(calculator.calculateToRubls(value, from.getNomianal(), from.getValue()));
            } else {
                callback.onSuccess(calculator.calculate(value, from.getNomianal(), from.getValue(),
                    to.getNomianal(), to.getValue()));
            }
        }
    }
}
