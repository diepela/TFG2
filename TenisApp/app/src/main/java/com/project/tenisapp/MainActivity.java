package com.project.tenisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definimos los dos botones que vamos a usar y el texto

        Button login = findViewById(R.id.button);
        Button register = findViewById(R.id.button2);

        // Al pulsar el botón 'Registrar' pasamos a la Actividad correspondiente sin pasarle ningún parámetro
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), RegistrarActivity.class);
                startActivity(intent);
                                    }
                                }
        );

        // Al pulsar el botón 'Login' iniciamos sesión y mandamos el usuario al fichero Shared Preferences
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences preferencias = getSharedPreferences
                        ("usuario", Context.MODE_PRIVATE);

                EditText et = findViewById(R.id.editText3);
                String user = et.getText().toString();

                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("user", user);

                editor.apply();

                et.setText("");

                Intent intent = new Intent(v.getContext(), MenuLogeadoActivity.class);
                startActivity(intent);
                                        }
                                    }
        );




    }



    }

