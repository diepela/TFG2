package com.project.tenisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        // Tomamos el dato a editar del bloque de texto introducid
        et = findViewById(R.id.editText);

    }

    public void editaDialogo (View v){

        // Mostramos el diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar el perfil");
        builder.setMessage("¿Seguro que quieres editar el perfil?");        // add the buttons
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editaPerfil();
            }
        });
        builder.setNegativeButton("Rechazar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void editaPerfil(){

        // Consulta a la base de datos para encontrar el usuario (siempre existirá ya que si no no se puede acceder a esta Actividad)
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Sacamos el nombre nuevo a colocar en el registro
        String nom = et.getText().toString();

        // Recuperamos de Shared Preferences el nombre de usuario logeado
        SharedPreferences preferencias = getSharedPreferences
                ("usuario", Context.MODE_PRIVATE);
        String user = preferencias.getString("user", "No existe el usuario");

        //Establecemos los campos-valores a actualizar
        ContentValues valores = new ContentValues();
        valores.put("nombre", nom);

        //Actualizamos el registro en la base de datos
        bd.update("usuarios", valores, "username='"+user+"'", null);

        Toast.makeText(this, "Datos de usuario editados",
                Toast.LENGTH_SHORT).show();

        // Volvemos a la actividad anterior
        finish();



    }

    public void deleteDialogo (final View v){

        // Sacamos el diálogo de confirmación al pulsar el botón Eliminar
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar el perfil");
        builder.setMessage("¿Seguro que quieres eliminar el perfil?");        // add the buttons
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminaPerfil(v);
            }
        });
        builder.setNegativeButton("Rechazar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void eliminaPerfil(View v){

        // Consulta a la base de datos para encontrar el usuario (siempre existirá ya que si no no se puede acceder a esta Actividad)
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        SharedPreferences preferencias = getSharedPreferences
                ("usuario", Context.MODE_PRIVATE);
        String user = preferencias.getString("user", "No existe el usuario");

        // Petición de eliminación del registro
        bd.delete("Usuarios", "username='" + user + "'", null);

        Toast.makeText(this, "Usuario eliminado",
                Toast.LENGTH_SHORT).show();

        // Volvemos a pantalla de inicio
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);

    }
}
