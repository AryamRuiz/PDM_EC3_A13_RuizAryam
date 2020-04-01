package com.example.tareassqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import com.androideity.tareasqlite.database.DBAdapter;
import com.androideity.tareasqlite.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    String srtCategory, srtSummary, srtDescription, srtButton;
    long _id;
    long idAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Main2Activity.this);

        final Funciones f = new Funciones();
        final EntitySQLite entity = new EntitySQLite();
        final TextView txtID = (TextView) findViewById(R.id.txtID);
        final TextView txtCategory = (TextView) findViewById(R.id.txtCategory);
        final TextView txtSummary = (TextView) findViewById(R.id.txtSummary);
        final TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        final Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        final Button btnEditar = (Button) findViewById(R.id.btnEditar);
        Button btnElimitar = (Button) findViewById(R.id.btnElimitar);
        Button btnRegresar = (Button) findViewById(R.id.btnRegresar);
        Button btnAll = (Button) findViewById(R.id.btnAll);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final DBAdapter sqldb2 = new DBAdapter(this);

        f.disabledTextView(txtCategory, txtSummary, txtDescription,txtID);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _id = Long.parseLong(txtID.getText().toString());
                try {
                    if (db != null) {
                        sqldb2.open();
                        Cursor query = sqldb2.fetchTblSQlite(_id);
                        if (query.getCount() > 0) {
                            txtCategory.setText(query.getString(1));
                            txtSummary.setText(query.getString(2));
                            txtDescription.setText(query.getString(3));
                        } else {
                            f.cleanTextView(txtCategory, txtSummary, txtDescription, txtID);
                            f.notFoundRegistro(Main2Activity.this);
                        }
                        sqldb2.close();
                    }
                } catch (Exception e) {
                    f.messageError(Main2Activity.this, e);
                }
            }

        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                srtButton = btnEditar.getText().toString();
                if(srtButton.equals("Editar")){
                f.enabledTextView(txtCategory, txtSummary, txtDescription, txtID);
                btnEditar.setText("Guardar cambio");
                }
                else if (srtButton.equals("Guardar cambio")) {
                    try {
                        dialogo1.setTitle("¡Alerta!");
                        dialogo1.setMessage("¿Está seguro de actualizar este registro?");
                        dialogo1.setCancelable(false);
                        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                idAux = Long.parseLong(txtID.getText().toString());
                                srtCategory = txtCategory.getText().toString();
                                srtSummary = txtSummary.getText().toString();
                                srtDescription = txtDescription.getText().toString();
                                    if (db != null) {
                                        sqldb2.open();
                                        sqldb2.updateTblSQlite(_id, srtCategory, srtSummary, srtDescription);
                                        f.cleanTextView(txtCategory, txtSummary, txtDescription, txtID);
                                        sqldb2.close();
                                    }
                                f.registroActualizado(Main2Activity.this);
                                f.disabledTextView(txtCategory,txtSummary,txtDescription,txtID);
                                btnEditar.setText("Editar");
                            }
                        });
                        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                            }
                        });
                        dialogo1.show();
                    } catch (Exception e) {
                        f.messageError(Main2Activity.this, e);
                    }
                }
            }
        });

        btnElimitar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    dialogo1.setTitle("¡Alerta!");
                    dialogo1.setMessage("¿Está seguro que desea borrar este registro?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            if (db != null) {
                                sqldb2.open();
                                sqldb2.deleteTblSQlite(_id);
                                f.cleanTextView(txtCategory, txtSummary, txtDescription, txtID);
                                sqldb2.close();
                            }
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                        }
                    });
                    dialogo1.show();
                } catch (Exception e) {
                    f.messageError(Main2Activity.this, e);
                }
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db != null) {
                    sqldb2.open();
                    Cursor query = sqldb2.fetchAllTblSQlite();
                    if (query.getCount() == 0) {
                        f.messageRegistros(Main2Activity.this, "¡Advertencia", "No se encontraron registros");
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (query.moveToNext()) {
                        buffer.append("ID : " + query.getString(0) + "\n");
                        buffer.append("Category : " + query.getString(1) + "\n");
                        buffer.append("Summary : " + query.getString(2) + "\n");
                        buffer.append("Description : " + query.getString(3) + "\n\n");
                    }
                    f.messageRegistros(Main2Activity.this, "Registros", buffer.toString());
                    sqldb2.close();
                }
            }
        });



        btnRegresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
