package ru.merkulyevsasha.excurrency.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ru.merkulyevsasha.excurrency.CurrencyApp;
import ru.merkulyevsasha.excurrency.R;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter pres;

    private EditText value;
    private EditText from;
    private EditText to;
    private View calculate;
    private TextView resultTextView;
    private View progressBar;

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
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        calculate = findViewById(R.id.calculate);
        progressBar = findViewById(R.id.progressbar);
        resultTextView = findViewById(R.id.result);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pres.calculate(Double.parseDouble(value.getText().toString()), from.getText().toString(), to.getText().toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        pres.dettachView();
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
}
