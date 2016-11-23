package com.lukefrozz.pedidos.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bbcha.db3";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String gifts = "CREATE TABLE gifts (" +
            "_id INTEGER PRIMARY KEY ASC AUTOINCREMENT, " +
            "active INTEGER (1) DEFAULT (1), " +
            "name TEXT NOT NULL, " +
            "description TEXT " +
        ");";

        String guests = "CREATE TABLE guests ( " +
            "_id INTEGER PRIMARY KEY ASC AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "gift_id INTEGER REFERENCES gifts (_id) ON DELETE SET NULL " +
            "ON UPDATE SET NULL " +
            "MATCH SIMPLE " +
        ");";
        sqLiteDatabase.execSQL(gifts);
        sqLiteDatabase.execSQL(guests);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos;");

        onCreate(sqLiteDatabase);
    }
}
