package ru.merkulyevsasha.domain;

import java.util.List;
import java.util.concurrent.ExecutorService;
import ru.merkulyevsasha.interactors.ConvertCurrencyCallback;
import ru.merkulyevsasha.interactors.CurrencyInteractor;
import ru.merkulyevsasha.repositories.CurrencyRepository;
import ru.merkulyevsasha.interactors.GetCurrenciesCalback;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.domain.usecase.CurrencyConverterUsecase;

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
        executor.submit(() -> {
            try {
                Currency from = repo.getCurrencyByNumCode(curFrom);
                Currency to = repo.getCurrencyByNumCode(curTo);
                currencyConverter.convert(value, curFrom, curTo, from, to, callback);
            } catch (Exception e) {
                e.printStackTrace();
                callback.onFailure();
            }
        });
    }

    @Override
    public void getCurrency(final GetCurrenciesCalback callback) {
        executor.submit(() -> {
            try {
                List<Currency> result = repo.getCurrencies();
                callback.onSuccess(result);
            } catch (Exception e) {
                e.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
