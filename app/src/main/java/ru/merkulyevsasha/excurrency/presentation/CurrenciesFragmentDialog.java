package ru.merkulyevsasha.excurrency.presentation;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.List;
import ru.merkulyevsasha.excurrency.R;

public class CurrenciesFragmentDialog extends DialogFragment {

    private final static String KEY_CURRENCIES = "KEY_CURRENCIES";
    private final static String KEY_CURRENCY_TYPE = "KEY_CURRENCY_TYPE";
    private List<String> currencies;
    private String typeCurrency;

    public static CurrenciesFragmentDialog getInstance(String typeCurrency, List<String> currencies) {
        CurrenciesFragmentDialog fragment = new CurrenciesFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(KEY_CURRENCIES, (ArrayList<String>) currencies);
        bundle.putString(KEY_CURRENCY_TYPE, typeCurrency);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY_CURRENCIES, (ArrayList<String>) currencies);
        outState.putString(KEY_CURRENCY_TYPE, typeCurrency);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (savedInstanceState != null) {
            args = savedInstanceState;
        }

        currencies = args.getStringArrayList(KEY_CURRENCIES);
        typeCurrency = args.getString(KEY_CURRENCY_TYPE);

        ListAdapter adapter = new ArrayAdapter<>(requireContext(), R.layout.item_dialog, currencies);
        return new AlertDialog.Builder(requireActivity())
            .setTitle(R.string.currency_select_title)
            .setSingleChoiceItems(adapter, 0, (dialog, which) -> {
                dialog.dismiss();
                Activity activity = requireActivity();
                if (activity instanceof OnCurrencyClick) {
                    ((OnCurrencyClick)activity).onCurrencyClicked(typeCurrency, currencies.get(which).substring(0, 3));
                }
            })
            .create();
    }

    public interface OnCurrencyClick {
        void onCurrencyClicked(String typeCurrency, String currency);
    }
}
