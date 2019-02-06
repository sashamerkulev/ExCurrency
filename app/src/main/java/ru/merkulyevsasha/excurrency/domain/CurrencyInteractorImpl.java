package ru.merkulyevsasha.excurrency.domain;

import java.util.List;
import java.util.concurrent.ExecutorService;
import ru.merkulyevsasha.excurrency.domain.calculator.CurrencyCalculator;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

public class CurrencyInteractorImpl implements CurrencyInteractor {

    private final CurrencyCalculator calculator;
    private final CurrencyRepository repo;
    private final ExecutorService executor;

    public CurrencyInteractorImpl(ExecutorService executor, CurrencyRepository repo, CurrencyCalculator calculator) {
        this.repo = repo;
        this.executor = executor;
        this.calculator = calculator;
    }

    @Override
    public void convert(final double value, final String curFrom, final String curTo, final ConvertCurrencyCallback callback) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Currency from = repo.getCurrencyByNumCode(curFrom);
                    Currency to = repo.getCurrencyByNumCode(curTo);
                    if (curTo.equals("RUB")) {
                        callback.onSuccess(calculator.calculate(value, to.getNomianal(), to.getValue()));
                    } else {
                        callback.onSuccess(calculator.calculate(value, from.getNomianal(), from.getValue(),
                            to.getNomianal(), to.getValue()));
                    }
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
