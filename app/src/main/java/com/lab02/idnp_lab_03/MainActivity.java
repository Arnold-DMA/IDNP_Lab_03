package com.lab02.idnp_lab_03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayDNI;
    ArrayList<String> arrayNombre;
    ArrayList<String> arrayApellido;
    ArrayList<String> arrayDireccion;
    ArrayList<String[]> arrayVisitas;
    TextView textViewDatos, textViewDatos2;
    Button btnPaciente;
    Button btnVisita;
    Button btnCorreo;
    EditText editTextPaciente;
    private static final int REQUEST_CODE_PACIENTE = 101;
    private static final int REQUEST_CODE_VISITA = 102;
    public static final String MESSAGE_DNI = "";
    public static final String MESSAGE_NOMBRE = "";
    public static final String MESSAGE_APELLIDO = "";
    public static final String MESSAGE_DIRECCION = "";
    public static final String MESSAGE_PESO = "";
    public static final String MESSAGE_SATURACION = "";
    public static final String MESSAGE_TEMPERATURA = "";
    public static final String MESSAGE_PRESION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comment
        setContentView(R.layout.activity_main);

        arrayDNI = new ArrayList<String>();
        arrayNombre = new ArrayList<String>();
        arrayApellido = new ArrayList<String>();
        arrayDireccion = new ArrayList<String>();
        arrayVisitas = new ArrayList<String[]>();

        textViewDatos = findViewById(R.id.textViewDatos);
        textViewDatos2 = findViewById(R.id.textViewDatos2);
        btnPaciente = findViewById(R.id.btnPaciente);
        btnVisita = findViewById(R.id.btnVisita);
        btnCorreo = findViewById(R.id.btnCorreo);
        editTextPaciente = findViewById(R.id.editTextPaciente);

        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), Paciente.class), REQUEST_CODE_PACIENTE);
            }
        });

        btnVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                int indice = arrayDNI.indexOf(editTextPaciente.getText().toString());
                String nombreCompleto = arrayNombre.get(indice)+" "+arrayApellido.get(indice);
                extras.putString("DNI", editTextPaciente.getText().toString());
                extras.putString("Nombre", nombreCompleto);
                Intent activityVisita = new Intent(getApplicationContext(), Visita.class);
                activityVisita.putExtras(extras);
                startActivityForResult(activityVisita, REQUEST_CODE_VISITA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_PACIENTE){
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
        else if(requestCode == REQUEST_CODE_VISITA){
            if(resultCode == RESULT_OK){
                arrayVisitas.add(new String[]{
                        data.getStringExtra(MESSAGE_DNI),
                        data.getStringExtra(MESSAGE_PESO),
                        data.getStringExtra(MESSAGE_TEMPERATURA),
                        data.getStringExtra(MESSAGE_PRESION),
                        data.getStringExtra(MESSAGE_SATURACION)
                });

                int cantidadVisitas = arrayVisitas.size();
                if(cantidadVisitas != 0){
                    textViewDatos2.setText("Catidad de visitas: "+cantidadVisitas+
                            "\nÚltimo visita registrada de paciente: "+data.getStringExtra(MESSAGE_DNI)+
                            "\n\tPeso: "+arrayVisitas.get(cantidadVisitas - 1)[1]+
                            "\n\tTemperatura: "+arrayVisitas.get(cantidadVisitas - 1)[2]+
                            "\n\tPresión: "+arrayVisitas.get(cantidadVisitas - 1)[3]+
                            "\n\tSaturación: "+arrayVisitas.get(cantidadVisitas - 1)[4]);
                }
                else{
                    textViewDatos.setText("Catidad de pacientes: "+cantidadVisitas);
                }
            }
            else if(resultCode == RESULT_CANCELED){
                Log.d("Cancelado", "Cancelado");
            }
        }
    }

}