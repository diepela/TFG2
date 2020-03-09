package com.project.tenisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    private EditText et1, et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Identificamos los dos bloques de texto que el usuario tendrá que rellenar
        et1 = findViewById(R.id.editText4);
        et2 = findViewById(R.id.editText5);


        // Ponemos los datos del array en el Spinner
        Spinner spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.brazobueno, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    public void alta(View v) {

        // Conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Obtenemos los datos de los bloques de texto y del Spinner
        String user = et1.getText().toString();
        String nom = et2.getText().toString();
        int brazo = 2;
        Spinner spinner = findViewById(R.id.spinner);
        String spin = spinner.getSelectedItem().toString();
        if (spin.equals("Derecho")) {
            brazo = 0;
        } else brazo = 1;

        // Hacemos la petición a la base de datos para comprobar que no exista un usuario con dicho user
        Cursor fila = bd.rawQuery("select * from usuarios where username='" + user + "'", null);

        if (fila.moveToFirst()) {

            // Informamos de que no es posible crear dicho usuario
            Toast.makeText(this, "Nombre de usuario ya existente", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");

        } else {

            // Registramos al usuario con los datos aportados
            ContentValues registro = new ContentValues();
            registro.put("username", user);
            registro.put("nombre", nom);
            registro.put("brazo", brazo);

            bd.insert("usuarios", null, registro);
            bd.close();

            et1.setText("");
            et2.setText("");

            Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();

            // Volvemos a la pantalla de inicio
            finish();

        }

    }
}


