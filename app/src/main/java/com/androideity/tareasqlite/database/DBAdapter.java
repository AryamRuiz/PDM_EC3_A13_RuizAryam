package com.androideity.tareasqlite.database;

//Imports necesarios para trabajar con SQLite

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    //Campos de la base de datos
    public static String KEY_ROWID = "_id";
    public static String KEY_CATEGORY = "category";
    public static String KEY_SUMMARY = "summary";
    public static String KEY_DESCRIPTION = "description";
    public static String DATABASE_TABLE = "tblSQLite";
    //Accesos
    private Context context;
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public DBAdapter(Context context) {
        this.context = context;
    }

    //Abre nuestra base de datos para recibir ordenes y cacha errores con Exception (Es como abrir la conexión)
    public DBAdapter open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //Cierra nuestro objeto Helper (Es como cerrar una conexión)
    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String category, String summary, String description) {
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, category);
        values.put(KEY_SUMMARY, summary);
        values.put(KEY_DESCRIPTION, description);
        return values;
    }

    //Crea nuestro campo
    public long createTblSQlite(String category, String summary, String description) {
        ContentValues initialValues = createContentValues(category, summary, description);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //Actualiza nuestro campo
    public boolean updateTblSQlite(long rowId, String category, String summary, String description) {
        ContentValues updateValues = createContentValues(category, summary, description);
        return db.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //Borra nuestro campo
    public boolean deleteTblSQlite(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //Retorna un objeto Cursor que contiene todos los datos
    public Cursor fetchAllTblSQlite() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION}, null, null, null, null, null);
    }

    //Retorna un objeto Cursor con la información de los campos
    public Cursor fetchTblSQlite(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
