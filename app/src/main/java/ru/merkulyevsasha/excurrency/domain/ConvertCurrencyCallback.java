package ru.merkulyevsasha.excurrency.domain;

public interface ConvertCurrencyCallback {

    void onSuccess(double result);
    void onFailure();

}
