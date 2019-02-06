package ru.merkulyevsasha.excurrency.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ru.merkulyevsasha.excurrency.CurrencyApp;
import ru.merkulyevsasha.excurrency.R;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pres = new MainPresenter(((CurrencyApp)getApplication()).getCurrencyFactory().getCurrencyInteractor());

        pres.attachView(this);
    }

    @Override
    protected void onDestroy() {
        pres.dettachView();
        pres = null;
        super.onDestroy();
    }
}
