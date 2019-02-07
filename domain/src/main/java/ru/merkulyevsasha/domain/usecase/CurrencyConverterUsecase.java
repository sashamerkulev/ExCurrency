package ru.merkulyevsasha.domain.usecase;

import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.interactors.ConvertCurrencyCallback;

public interface CurrencyConverterUsecase {
    void convert(double value, String curFrom, String curTo,
        Currency from, Currency to, ConvertCurrencyCallback callback);
}
