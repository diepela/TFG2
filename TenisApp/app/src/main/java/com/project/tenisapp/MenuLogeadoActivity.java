package com.project.tenisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuLogeadoActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_logeado);

        // Obtenemos datos de nombre del usuario y lo mostramos por pantalla
        text = findViewById(R.id.textView9);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        SharedPreferences preferencias = getSharedPreferences
                ("usuario", Context.MODE_PRIVATE);
        String user = preferencias.getString("user", "No existe el usuario");

        Cursor fila = bd.rawQuery("select * from usuarios where username='"+user+"'", null);

        // If-else de precaución por si no existiera el registro
        if (fila.moveToFirst()) {
            text.setText(fila.getString(2));
        } else {
            Toast.makeText(this, "No existe ese usuario",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        bd.close();

        // Al pulsar el botón editar lo mandamos a dicha actividad
        Button editar = findViewById(R.id.button3);

        editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditarActivity.class);
                startActivity(intent);
            }
        }
        );


    }



    public void cerrarSesion(View v){

        SharedPreferences preferencias = getSharedPreferences
                ("usuario", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("user", "");

        editor.apply();

        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);

    }
}
