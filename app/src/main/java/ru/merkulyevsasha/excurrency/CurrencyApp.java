package ru.merkulyevsasha.excurrency;

import android.app.Application;
import ru.merkulyevsasha.factories.CurrencyFactory;

public class CurrencyApp extends Application {

    private CurrencyFactory _factory = null;

    @Override
    public void onCreate() {
        super.onCreate();
        _factory = new CurrencyFactory(this);
    }

    public CurrencyFactory getCurrencyFactory() {
        return _factory;
    }
}
