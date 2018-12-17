package id.co.rinaano.saturney2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBSaturney extends SQLiteOpenHelper{

    static abstract class MyColumns implements BaseColumns{
        static final String NamaTabel = "Produk";
        static final String Nama = "Nama";
        static final String Detail = "Detail";
        static final String Harga = "Harga";
    }

    private static final String NamaDatabse = "saturney.db";
    private static final int VersiDatabase = 14;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+MyColumns.NamaTabel+
            "("+MyColumns.Nama+" TEXT PRIMARY KEY, "+MyColumns.Detail+" TEXT NOT NULL, "+MyColumns.Harga+
            " TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+MyColumns.NamaTabel;

    DBSaturney(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}