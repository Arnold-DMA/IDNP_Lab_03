package com.lab02.idnp_lab_03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayDNI;
    ArrayList<String> arrayNombre;
    ArrayList<String> arrayApellido;
    ArrayList<String> arrayDireccion;
    TextView textViewDatos;
    Button btnPaciente;
    private static final int REQUEST_CODE = 101;
    public static final String MESSAGE_DNI = "";
    public static final String MESSAGE_NOMBRE = "";
    public static final String MESSAGE_APELLIDO = "";
    public static final String MESSAGE_DIRECCION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comment
        setContentView(R.layout.activity_main);

        arrayDNI = new ArrayList<String>();
        arrayNombre = new ArrayList<String>();
        arrayApellido = new ArrayList<String>();
        arrayDireccion = new ArrayList<String>();

        textViewDatos = findViewById(R.id.textViewDatos);
        btnPaciente = findViewById(R.id.btnPaciente);
        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), Paciente.class), REQUEST_CODE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                arrayDNI.add(data.getStringExtra(MESSAGE_DNI));
                arrayNombre.add(data.getStringExtra(MESSAGE_NOMBRE));
                arrayApellido.add(data.getStringExtra(MESSAGE_APELLIDO));
                arrayDireccion.add(data.getStringExtra(MESSAGE_DIRECCION));
                int cantidadPacientes = arrayDNI.size();
                if(cantidadPacientes != 0){
                    textViewDatos.setText("Catidad de pacientes: "+cantidadPacientes+
                            "\nÚltimo paciente registrado: "+
                            "\n\tDNI: "+arrayDNI.get(cantidadPacientes - 1)+
                            "\n\tNombre: "+arrayNombre.get(cantidadPacientes - 1)+
                            "\n\tApellido: "+arrayApellido.get(cantidadPacientes - 1)+
                            "\n\tDirección: "+arrayDireccion.get(cantidadPacientes - 1));
                }
                else{
                    textViewDatos.setText("Catidad de pacientes: "+cantidadPacientes);
                }
            }
            else if(resultCode == RESULT_CANCELED){
                Log.d("Cancelado", "Cancelado");
            }
        }
    }
    
}