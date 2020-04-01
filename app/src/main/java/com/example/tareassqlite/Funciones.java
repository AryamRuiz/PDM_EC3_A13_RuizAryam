package com.example.tareassqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class Funciones {

    public void disabledTextView(TextView category, TextView summary, TextView description, TextView id) {
        category.setEnabled(false);
        summary.setEnabled(false);
        description.setEnabled(false);
        id.setEnabled(true);
    }

    public void enabledTextView(TextView category, TextView summary, TextView description, TextView id) {
        category.setEnabled(true);
        summary.setEnabled(true);
        description.setEnabled(true);
        id.setEnabled(false);
    }

    public void cleanTextView(TextView category, TextView summary, TextView description, TextView id) {
        category.setText("");
        summary.setText("");
        description.setText("");
        id.setText("");
    }

    public void cleanTextViewRegister(TextView category, TextView summary, TextView description) {
        category.setText("");
        summary.setText("");
        description.setText("");
    }

    public Boolean validationText(String category, String summary, String description){
        if(category.equals("") && summary.equals("") && description.equals("") ){
            return false;
        }else {
            return true;
        }
    }

    public void registroExitoso(Context main) {
        Toast.makeText(main,"El registro fue realizado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void registroActualizado(Context main) {
        Toast.makeText(main,"El registro fue actualizado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void registroEliminado(Context main) {
        Toast.makeText(main,"Se registro fue eliminado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void messageError(Context main, Exception e) {
        Toast.makeText(main,"Error: "+e, Toast.LENGTH_SHORT).show();
    }

    public void notFoundRegistro(Context main) {
        Toast.makeText(main,"El registro no fue encontrado", Toast.LENGTH_SHORT).show();
    }

    public void messageVacio(Context main) {
        Toast.makeText(main,"Se encontraron campos vacios", Toast.LENGTH_SHORT).show();
    }

    public void messageRegistros(Context main, String titulo, String msj) {
        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(msj);
        builder.show();
    }

}
