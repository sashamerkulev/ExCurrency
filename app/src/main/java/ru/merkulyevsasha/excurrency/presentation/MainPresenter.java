package ru.merkulyevsasha.excurrency.presentation;

import java.util.List;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.interactors.ConvertCurrencyCallback;
import ru.merkulyevsasha.interactors.CurrencyInteractor;
import ru.merkulyevsasha.interactors.GetCurrenciesCalback;

class MainPresenter {

    private final CurrencyInteractor interactor;
    private MainView view;

    MainPresenter(CurrencyInteractor interactor) {
        this.interactor = interactor;
    }

    void attachView(MainView view) {
        this.view = view;
    }

    void detachView() {
        this.view = null;
    }

    void calculate(double value, String from, String to) {
        if (view == null) return;
        view.showProgress();
        interactor.convert(value, from, to, new ConvertCurrencyCallback() {
            @Override
            public void onSuccess(double result) {
                if (view == null) return;
                view.hideProgress();
                view.showResult(result);
            }

            @Override
            public void onFailure() {
                if (view == null) return;
                view.hideProgress();
                view.showErrorMessage();
            }
        });
    }

    void onCreate() {
        interactor.getCurrency(new GetCurrenciesCalback() {
            @Override
            public void onSuccess(List<Currency> currencies) {
                if (view == null) return;
                currencies.add(0, new Currency("RUB", "RUB", 1, "Российский рубль", 1));
                view.storeCurrencies(currencies);
            }

            @Override
            public void onFailure() {
            }
        });
    }
}
