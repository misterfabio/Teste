package com.fabiocarvalho.teste.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TesteOpenHelper extends SQLiteOpenHelper {

    public TesteOpenHelper(Context context) {
        super(context, "BDTESTE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ScriptsDDL.getCreateTableCatalogo());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
