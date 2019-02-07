package ru.merkulyevsasha.excurrency.presentation;

import java.util.List;
import ru.merkulyevsasha.core.models.Currency;

public interface MainView {
    void showProgress();

    void hideProgress();

    void showResult(double result);

    void showErrorMessage();

    void storeCurrencies(List<Currency> currencies);
}
