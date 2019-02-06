package ru.merkulyevsasha.excurrency.domain;

import java.util.List;
import java.util.concurrent.ExecutorService;
import ru.merkulyevsasha.excurrency.domain.models.Currency;
import ru.merkulyevsasha.excurrency.domain.usecase.CurrencyConverterUsecase;

public class CurrencyInteractorImpl implements CurrencyInteractor {

    private final CurrencyConverterUsecase currencyConverter;
    private final CurrencyRepository repo;
    private final ExecutorService executor;

    public CurrencyInteractorImpl(ExecutorService executor, CurrencyRepository repo, CurrencyConverterUsecase currencyConverter) {
        this.repo = repo;
        this.executor = executor;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public void convert(final double value, final String curFrom, final String curTo, final ConvertCurrencyCallback callback) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Currency from = repo.getCurrencyByNumCode(curFrom);
                    Currency to = repo.getCurrencyByNumCode(curTo);
                    currencyConverter.convert(value, curFrom, curTo, from, to, callback);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure();
                }
            }
        });
    }

    @Override
    public void getCurrency(final GetCurrenciesCalback callback) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Currency> result = repo.getCurrencies();
                    callback.onSuccess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure();
                }
            }
        });
    }
}
