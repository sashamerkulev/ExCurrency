package ru.merkulyevsasha.excurrency.presentation;

import java.util.List;
import ru.merkulyevsasha.excurrency.domain.ConvertCurrencyCallback;
import ru.merkulyevsasha.excurrency.domain.CurrencyInteractor;
import ru.merkulyevsasha.excurrency.domain.GetCurrenciesCalback;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

class MainPresenter {

    private final CurrencyInteractor interactor;
    private MainView view;

    MainPresenter(CurrencyInteractor interactor) {
        this.interactor = interactor;
    }

    void attachView(MainView view) {
        this.view = view;
    }

    void dettachView() {
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
                currencies.add(0, new Currency("RUB", "RUB", 1, "Россйский рубль", 1));
                view.storeCurrencies(currencies);
            }

            @Override
            public void onFailure() {
            }
        });
    }
}
