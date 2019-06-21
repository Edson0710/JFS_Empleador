package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro_2 extends AppCompatActivity {
    String nombre, giro, correo, contra, direccion, telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_2);

        String ivan = getIntent().getExtras().getString("Ivan");
        nombre = getIntent().getExtras().getString("nombre");
        giro = getIntent().getExtras().getString("giro");
        correo = getIntent().getExtras().getString("correo");
        contra = getIntent().getExtras().getString("contra");
        direccion = getIntent().getExtras().getString("direccion");
        telefono = getIntent().getExtras().getString("telefono");

        Toast.makeText(Registro_2.this, ""+nombre, Toast.LENGTH_SHORT).show();
        Toast.makeText(Registro_2.this, ""+giro, Toast.LENGTH_SHORT).show();
        Toast.makeText(Registro_2.this, ""+correo, Toast.LENGTH_SHORT).show();
        Toast.makeText(Registro_2.this, ""+contra, Toast.LENGTH_SHORT).show();
        Toast.makeText(Registro_2.this, ""+direccion, Toast.LENGTH_SHORT).show();
        Toast.makeText(Registro_2.this, ""+telefono, Toast.LENGTH_SHORT).show();
    }


    //-------------------------Botones--------------------------------------------------------------
    //Metodo boton regresar
    public void Regresar (View view){

        Intent intent = new Intent(Registro_2.this, Registro_1.class);
        startActivity(intent);
    }

    //Metodo boton continuar
    public void Continuar (View view){

        Intent intent = new Intent(Registro_2.this, Registro_3.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------
}
