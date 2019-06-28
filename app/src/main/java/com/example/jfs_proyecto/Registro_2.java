package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro_2 extends AppCompatActivity {
    String nombre, giro, correo, contra, direccion, telefono;
    RadioButton si1_rb, si2_rb, si3_rb;
    EditText otro1_etxt, otro2_etxt, otro3_etxt;
    String otro1, otro2, otro3, transporte, comisiones, bonos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_2);

        si1_rb = findViewById(R.id.Registro2_rboton_si1);
        si2_rb = findViewById(R.id.Registro2_rboton_si2);
        si3_rb = findViewById(R.id.Registro2_rboton_si3);
        otro1_etxt = findViewById(R.id.Registro2_etxt_otro1);
        otro2_etxt = findViewById(R.id.Registro2_etxt_otro2);
        otro3_etxt = findViewById(R.id.Registro2_etxt_otro3);

        nombre = getIntent().getExtras().getString("nombre");
        giro = getIntent().getExtras().getString("giro");
        correo = getIntent().getExtras().getString("correo");
        contra = getIntent().getExtras().getString("contra");
        direccion = getIntent().getExtras().getString("direccion");
        telefono = getIntent().getExtras().getString("telefono");

    }


    //-------------------------Botones--------------------------------------------------------------
    //Metodo boton regresar
    public void Regresar (View view){

        Intent intent = new Intent(Registro_2.this, Registro_1.class);
        startActivity(intent);
    }

    //Metodo boton continuar
    public void Continuar (View view){
        RevisarRadioButtons();
        otro1 = otro1_etxt.getText().toString().trim();
        otro2 = otro2_etxt.getText().toString().trim();
        otro3 = otro3_etxt.getText().toString().trim();
        Intent intent = new Intent(Registro_2.this, Registro_3.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("giro", giro);
        intent.putExtra("correo", correo);
        intent.putExtra("contra", contra);
        intent.putExtra("direccion", direccion);
        intent.putExtra("telefono", telefono);
        intent.putExtra("transporte", transporte);
        intent.putExtra("comisiones", comisiones);
        intent.putExtra("bonos", bonos);
        intent.putExtra("otro1", otro1);
        intent.putExtra("otro2", otro2);
        intent.putExtra("otro3", otro3);
        startActivity(intent);
    }
    //---------------------------Funciones----------------------------------------------------------
    //Funcion para obtener estado de Radio Buttons
    public  void RevisarRadioButtons(){
        if (si1_rb.isChecked()){
            transporte = "1";
        } else {
            transporte = "0";
        }

        if (si2_rb.isChecked()){
            comisiones = "1";
        } else {
            comisiones = "0";
        }

        if (si3_rb.isChecked()){
            bonos = "1";
        } else {
            bonos = "0";
        }
    }
}
