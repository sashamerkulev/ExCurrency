package ru.merkulyevsasha.excurrency.presentation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import java.util.List;
import ru.merkulyevsasha.excurrency.R;

public class CurrenciesFragmentDialog extends DialogFragment {

    List<String> currencies;
    OnCurrencyClick clickListener;

    public static CurrenciesFragmentDialog getInstance(List<String> currencies, OnCurrencyClick clickListener) {
        CurrenciesFragmentDialog fragment = new CurrenciesFragmentDialog();
        fragment.currencies = currencies;
        fragment.clickListener = clickListener;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ListAdapter adapter = new ArrayAdapter<>(requireContext(), R.layout.item_dialog, currencies);
        return new AlertDialog.Builder(requireActivity())
            .setTitle(R.string.currency_select_title)
            .setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    clickListener.onCurrencyClicked(currencies.get(which));
                }
            })
            .create();
    }

    public interface OnCurrencyClick {
        void onCurrencyClicked(String currency);
    }
}
