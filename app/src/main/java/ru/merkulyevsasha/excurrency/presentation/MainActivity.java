package ru.merkulyevsasha.excurrency.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import ru.merkulyevsasha.excurrency.CurrencyApp;
import ru.merkulyevsasha.excurrency.R;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter pres;

    private EditText value;
    private TextView fromCurrency;
    private TextView toCurrency;
    private View calculate;
    private TextView resultTextView;
    private View progressBar;

    private final List<Currency> currencies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();

        pres = new MainPresenter(((CurrencyApp)getApplication()).getCurrencyFactory().getCurrencyInteractor());
        pres.attachView(this);
        pres.onCreate();
    }

    private void initControls() {
        value = findViewById(R.id.value);
        fromCurrency = findViewById(R.id.from);
        toCurrency = findViewById(R.id.to);
        calculate = findViewById(R.id.calculate);
        progressBar = findViewById(R.id.progressbar);
        resultTextView = findViewById(R.id.result);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double result = Double.parseDouble(value.getText().toString().replace(",","."));
                    pres.calculate(result, fromCurrency.getText().toString(), toCurrency.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    showValidateErrorMessage();
                }
            }
        });
        fromCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrenciesFragmentDialog dialog = CurrenciesFragmentDialog.getInstance(getCurrencyCodes(currencies), new CurrenciesFragmentDialog.OnCurrencyClick() {
                    @Override
                    public void onCurrencyClicked(String currency) {
                        fromCurrency.setText(currency);
                    }
                });
                dialog.show(getSupportFragmentManager(), "d1");
            }
        });
        toCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrenciesFragmentDialog dialog = CurrenciesFragmentDialog.getInstance(getCurrencyCodes(currencies), new CurrenciesFragmentDialog.OnCurrencyClick() {
                    @Override
                    public void onCurrencyClicked(String currency) {
                        toCurrency.setText(currency);
                    }
                });
                dialog.show(getSupportFragmentManager(), "d2");
            }
        });
    }

    @Override
    protected void onDestroy() {
        pres.detachView();
        pres = null;
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                calculate.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                calculate.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void showResult(final double result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText(String.valueOf(result));
            }
        });
    }

    @Override
    public void showErrorMessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText("");
                Toast.makeText(MainActivity.this, getString(R.string.convert_error_message), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void storeCurrencies(List<Currency> currencies) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
    }

    private void showValidateErrorMessage() {
        Toast.makeText(MainActivity.this, getString(R.string.validate_error_message), Toast.LENGTH_LONG).show();
    }

    private List<String> getCurrencyCodes(List<Currency> currencies) {
        List<String> codes = new ArrayList<>();
        for(Currency currency: currencies) {
            codes.add(currency.getChrCode());
        }
        return codes;
    }

}
