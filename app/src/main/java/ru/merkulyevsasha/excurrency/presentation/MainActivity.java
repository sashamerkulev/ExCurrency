package ru.merkulyevsasha.excurrency.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.excurrency.CurrencyApp;
import ru.merkulyevsasha.excurrency.R;

public class MainActivity extends AppCompatActivity implements MainView, CurrenciesFragmentDialog.OnCurrencyClick {

    private final static String CURRENCY_TYPE_FROM = "CURRENCY_TYPE_FROM";
    private final static String CURRENCY_TYPE_TO = "CURRENCY_TYPE_TO";

    private final static String KEY_CURRENCY_VALUE = "KEY_CURRENCY_VALUE";
    private final static String KEY_CURRENCY_FROM = "KEY_CURRENCY_FROM";
    private final static String KEY_CURRENCY_TO = "KEY_CURRENCY_TO";
    private final static String KEY_CURRENCY_RESULT = "KEY_CURRENCY_RESULT";

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

        initControls(savedInstanceState);

        pres = new MainPresenter(((CurrencyApp) getApplication()).getCurrencyFactory().getCurrencyInteractor());
        pres.attachView(this);
        pres.onCreate();
    }

    @Override
    protected void onDestroy() {
        pres.detachView();
        pres = null;
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        runOnUiThread(() -> {
            progressBar.setVisibility(View.VISIBLE);
            calculate.setVisibility(View.GONE);
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(() -> {
            progressBar.setVisibility(View.GONE);
            calculate.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void showResult(final double result) {
        runOnUiThread(() -> resultTextView.setText(String.valueOf(result)));
    }

    @Override
    public void showErrorMessage() {
        runOnUiThread(() -> {
            resultTextView.setText("");
            Toast.makeText(MainActivity.this, getString(R.string.convert_error_message), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void storeCurrencies(List<Currency> currencies) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
    }

    @Override
    public void onCurrencyClicked(String typeCurrency, String currency) {
        if (typeCurrency.equals(CURRENCY_TYPE_FROM)) {
            fromCurrency.setText(currency);
        } else if (typeCurrency.equals(CURRENCY_TYPE_TO)) {
            toCurrency.setText(currency);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CURRENCY_VALUE, value.getText().toString());
        outState.putString(KEY_CURRENCY_FROM, fromCurrency.getText().toString());
        outState.putString(KEY_CURRENCY_TO, toCurrency.getText().toString());
        outState.putString(KEY_CURRENCY_RESULT, resultTextView.getText().toString());
    }

    private void initControls(Bundle savedInstanceState) {
        value = findViewById(R.id.value);
        fromCurrency = findViewById(R.id.from);
        toCurrency = findViewById(R.id.to);
        calculate = findViewById(R.id.calculate);
        progressBar = findViewById(R.id.progressbar);
        resultTextView = findViewById(R.id.result);
        if (savedInstanceState != null) {
            value.setText(savedInstanceState.getString(KEY_CURRENCY_VALUE));
            fromCurrency.setText(savedInstanceState.getString(KEY_CURRENCY_FROM));
            toCurrency.setText(savedInstanceState.getString(KEY_CURRENCY_TO));
            resultTextView.setText(savedInstanceState.getString(KEY_CURRENCY_RESULT));
        }
        calculate.setOnClickListener(v -> {
            try {
                double result = Double.parseDouble(value.getText().toString().replace(",", "."));
                pres.calculate(result, fromCurrency.getText().toString(), toCurrency.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                showValidateErrorMessage();
            }
        });
        fromCurrency.setOnClickListener(v -> {
            CurrenciesFragmentDialog dialog = CurrenciesFragmentDialog.getInstance(CURRENCY_TYPE_FROM, getCurrencyCodes(currencies));
            dialog.show(getSupportFragmentManager(), CURRENCY_TYPE_FROM);
        });
        toCurrency.setOnClickListener(v -> {
            CurrenciesFragmentDialog dialog = CurrenciesFragmentDialog.getInstance(CURRENCY_TYPE_TO, getCurrencyCodes(currencies));
            dialog.show(getSupportFragmentManager(), CURRENCY_TYPE_TO);
        });
    }

    private void showValidateErrorMessage() {
        Toast.makeText(MainActivity.this, getString(R.string.validate_error_message), Toast.LENGTH_LONG).show();
    }

    private List<String> getCurrencyCodes(List<Currency> currencies) {
        List<String> codes = new ArrayList<>();
        for (Currency currency : currencies) {
            codes.add(currency.getChrCode() + " - " + currency.getName());
        }
        return codes;
    }
}
