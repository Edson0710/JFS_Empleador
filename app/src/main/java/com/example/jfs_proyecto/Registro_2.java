package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Registro_2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_2);
    }



    //-------------------------Botones--------------------------------------------------------------
    //Metodo boton regresar
    public void Regresar (View view){

        Intent regresar = new Intent(Registro_2.this, Registro_1.class);
        startActivity(regresar);
    }

    //Metodo boton continuar
    public void Continuar (View view){

        Intent continuar = new Intent(Registro_2.this, Registro_3.class);
        startActivity(continuar);
    }
    //----------------------------------------------------------------------------------------------
}
