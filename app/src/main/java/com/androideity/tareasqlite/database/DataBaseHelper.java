package com.androideity.tareasqlite.database;

//Imports necesarios para trabajar con SQLite
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

    //Nombre de la base de datos, Versión de la base de datos, Codigo de creación de la base de datos.
    private static final String DATABASE_NAME = "ApplicationSQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table tblSQLite (_id integer primary key autoincrement, " +
            " category text not null, " +
            "summary text not null, " +
            "description text not null)";

    //Ayuda abrir, crear, administrar una base de datos.
    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Ejecuta la consulta para crear la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE);
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Maneja acciones en la actualización de base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Se actualizo la versión "+oldVersion+" a "+newVersion+", esto destruirá la versión anterior.");
        db.execSQL("DROP TABLE IF EXISTS tblSQLite");
        onCreate(db);
    }
}
