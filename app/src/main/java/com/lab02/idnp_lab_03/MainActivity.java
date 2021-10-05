package com.lab02.idnp_lab_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayDNI, arrayNombre, arrayApellido, arrayDireccion;
    TextView textViewDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comment
        setContentView(R.layout.activity_main);

        arrayDNI = new ArrayList<String>();
        arrayNombre = new ArrayList<String>();
        arrayApellido = new ArrayList<String>();
        arrayDireccion = new ArrayList<String>();
        //visitas = new ArrayList<String[]>();

        textViewDatos = findViewById(R.id.textViewDatos);
        Button btnPaciente = findViewById(R.id.btnPaciente);
        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pacienteIntent = new Intent(getApplicationContext(), Paciente.class);
                /*Bundle arrayPacientes = new Bundle();
                arrayPacientes.putStringArrayList("DNI", arrayDNI);
                arrayPacientes.putStringArrayList("Nombre", arrayNombre);
                arrayPacientes.putStringArrayList("Apellido", arrayApellido);
                arrayPacientes.putStringArrayList("Dirección", arrayDireccion);
                pacienteIntent.putExtras(arrayPacientes);*/
                startActivity(pacienteIntent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            arrayDNI.add(parametros.getString("DNI"));
            arrayNombre.add(parametros.getString("Nombre"));
            arrayApellido.add(parametros.getString("Apellido"));
            arrayDireccion.add(parametros.getString("Direccion"));
        }
        int cantidadPacientes = arrayDNI.size();
        if(cantidadPacientes != 0){
            textViewDatos.setText("Datos de paciente"+
                    "\nDNI: "+arrayDNI.get(cantidadPacientes - 1)+
                    "\nNombre: "+arrayNombre.get(cantidadPacientes - 1)+
                    "\nApellido: "+arrayApellido.get(cantidadPacientes - 1)+
                    "\nDirección: "+arrayDireccion.get(cantidadPacientes - 1));
        }
        else{
            textViewDatos.setText("Datos de paciente");
        }

    }

    /*@Override
    protected void onRestart() {
        super.onRestart();

        /*Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            arrayDNI.add(parametros.getString("DNI"));
        }

        //int cantidadPacientes = arrayDNI.size();
        /*if(cantidadPacientes != 0){
            textViewDatos.setText("Hola");
        }
        else{
            textViewDatos.setText("adios");
        }
    }*/
}