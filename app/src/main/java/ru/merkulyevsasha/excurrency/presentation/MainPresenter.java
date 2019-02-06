package ru.merkulyevsasha.excurrency.presentation;

import ru.merkulyevsasha.excurrency.domain.CurrencyInteractor;

public class MainPresenter {

    private final CurrencyInteractor interactor;
    private MainView view;

    public MainPresenter(CurrencyInteractor interactor) {
        this.interactor = interactor;
    }

    public void attachView(MainView view) {
        this.view = view;
    }

    public void dettachView() {
        this.view = null;
    }

}
