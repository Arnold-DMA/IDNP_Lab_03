package com.lab02.idnp_lab_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Paciente extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        /*ArrayList<String> arrayDNI = getIntent().getExtras().getStringArrayList("DNI");
        ArrayList<String> arrayNombre = getIntent().getExtras().getStringArrayList("Nombre");
        ArrayList<String> arrayApellido = getIntent().getExtras().getStringArrayList("Apellido");
        ArrayList<String> arrayDireccion = getIntent().getExtras().getStringArrayList("Direccion");*/

        EditText editTextDNI = findViewById(R.id.editTextDNI);
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        EditText editTextApellido = findViewById(R.id.editTextApellido);
        EditText editTextDireccion = findViewById(R.id.editTextDireccion);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*arrayDNI.add(editTextDNI.getText().toString());
                arrayNombre.add(editTextNombre.getText().toString());
                arrayApellido.add(editTextApellido.getText().toString());
                arrayDireccion.add(editTextDireccion.getText().toString());*/
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle arrayPacientes = new Bundle();
                arrayPacientes.putString("DNI", editTextDNI.getText().toString());
                arrayPacientes.putString("Nombre", editTextNombre.getText().toString());
                arrayPacientes.putString("Apellido", editTextApellido.getText().toString());
                arrayPacientes.putString("Direccion", editTextDireccion.getText().toString());
                mainIntent.putExtras(arrayPacientes);
                startActivity(mainIntent);

            }
        });


    }
}