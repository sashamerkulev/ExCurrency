package ru.merkulyevsasha.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import ru.merkulyevsasha.core.models.Currency;

public class CurrencyDatabaseImpl extends SQLiteOpenHelper implements CurrencyDatabase {

    private static final String DATABASE_NAME = "currencies.db";
    private static final int VERSION = 1;

    private static final String CURRENCY_TABLE_NAME = "currency";
    private static final String CURRENCY_NUM_CODE = "numCode";
    private static final String CURRENCY_CHR_CODE = "chrCode";
    private static final String CURRENCY_NOMINAL = "nominal";
    private static final String CURRENCY_NAME = "name";
    private static final String CURRENCY_VALUE = "value";

    private static final String CREATE_CURRENCY_TABLE = "create table " + CURRENCY_TABLE_NAME + " ( " +
        CURRENCY_NUM_CODE + " text, " +
        CURRENCY_CHR_CODE + " text, " +
        CURRENCY_NOMINAL + " integer, " +
        CURRENCY_NAME + " text, " +
        CURRENCY_VALUE + " double " +
        " ); ";

    private static final String CREATE_CURRENCY_INDEX = "create unique index " + CURRENCY_TABLE_NAME + "_" + CURRENCY_NUM_CODE +
        " ON " + CURRENCY_TABLE_NAME + "(" + CURRENCY_NUM_CODE + ");";

    public CurrencyDatabaseImpl(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CURRENCY_TABLE);
        db.execSQL(CREATE_CURRENCY_INDEX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @NonNull
    private Currency getCurrencyFromCursor(@NonNull final Cursor cursor) {
        String numCode = cursor.getString(cursor.getColumnIndex(CURRENCY_NUM_CODE));
        String chrCode = cursor.getString(cursor.getColumnIndex(CURRENCY_CHR_CODE));
        int nominal = cursor.getInt(cursor.getColumnIndex(CURRENCY_NOMINAL));
        String name = cursor.getString(cursor.getColumnIndex(CURRENCY_NAME));
        double value = cursor.getDouble(cursor.getColumnIndex(CURRENCY_VALUE));
        return new Currency(numCode, chrCode, nominal, name, value);
    }

    @Nullable
    @Override
    public Currency getCurrencyByNumCode(@NonNull String numCode) {
        final String select = "select " + CURRENCY_NUM_CODE + ", " +
            CURRENCY_CHR_CODE + ", " + CURRENCY_NOMINAL + ", " + CURRENCY_NAME + ", " + CURRENCY_VALUE +
            " from " + CURRENCY_TABLE_NAME +
            " where " + CURRENCY_CHR_CODE + " = ?";
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery(select, new String[] { numCode })) {
            if (cursor.moveToFirst()) {
                return getCurrencyFromCursor(cursor);
            }
        }
        return null;
    }

    @Override
    public void deleteCurrencies() {
        final String delete = "delete from " + CURRENCY_TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(delete);
    }

    @Override
    public void addCurrencies(@NonNull List<Currency> currencies) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            for (Currency currency : currencies) {
                ContentValues values = new ContentValues();
                values.put(CURRENCY_NUM_CODE, currency.getNumCode());
                values.put(CURRENCY_CHR_CODE, currency.getChrCode());
                values.put(CURRENCY_NOMINAL, currency.getNomianal());
                values.put(CURRENCY_NAME, currency.getName());
                values.put(CURRENCY_VALUE, currency.getValue());
                db.insertWithOnConflict(CURRENCY_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @NonNull
    @Override
    public List<Currency> getCurrencies() {
        final String select = "select " + CURRENCY_NUM_CODE + ", " +
            CURRENCY_CHR_CODE + ", " + CURRENCY_NOMINAL + ", " + CURRENCY_NAME + ", " + CURRENCY_VALUE +
            " from " + CURRENCY_TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        List<Currency> result = new ArrayList<>();
        try (Cursor cursor = db.rawQuery(select, null)) {
            if (cursor.moveToFirst()) {
                do {
                    result.add(getCurrencyFromCursor(cursor));
                } while (cursor.moveToNext());
            }
            return result;
        }
    }
}