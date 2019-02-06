package ru.merkulyevsasha.excurrency.domain;

public interface CurrencyInteractor {


    void convert(double value, String curFrom, String curTo, ConvertCurrencyCallback callback);

    void getCurrency(GetCurrenciesCalback callback);

}
