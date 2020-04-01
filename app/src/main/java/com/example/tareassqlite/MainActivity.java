package com.example.tareassqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androideity.tareasqlite.database.DBAdapter;
import com.androideity.tareasqlite.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String srtCategory, srtSummary, srtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Funciones f = new Funciones();
        final EditText txtCategory = (EditText) findViewById(R.id.txtCategory);
        final EditText txtSummary = (EditText) findViewById(R.id.txtSummary);
        final EditText txtDescription = (EditText) findViewById(R.id.txtDescription);
        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        Button btnVista = (Button) findViewById(R.id.btnVista);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final DBAdapter sqldb = new DBAdapter(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srtCategory = txtCategory.getText().toString();
                srtSummary = txtSummary.getText().toString();
                srtDescription = txtDescription.getText().toString();
                try {
                    if ((f.validationText(srtCategory, srtSummary, srtDescription))) {
                        if (db != null) {
                            sqldb.open();
                            if (sqldb.createTblSQlite(srtCategory, srtSummary, srtDescription) != -1) {
                                f.cleanTextViewRegister(txtCategory,txtSummary,txtDescription);
                                f.registroExitoso(MainActivity.this);
                            }
                            sqldb.close();
                        }
                    } else {
                        f.messageVacio(MainActivity.this);
                    }
                } catch (Exception e) {
                    f.messageError(MainActivity.this, e);
                }
            }
        });


        btnVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
