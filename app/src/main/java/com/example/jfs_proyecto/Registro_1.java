package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registro_1 extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_1);
    }

    //-------------------------Botones--------------------------------------------------------------
    //Metodo boton cancelar
    public void Cancelar (View view){

        Intent cancelar = new Intent(Registro_1.this, Login.class);
        startActivity(cancelar);
    }

    //Metodo boton continuar
    public void Continuar (View view){

        Intent continuar = new Intent(Registro_1.this, Registro_2.class);
        startActivity(continuar);
    }
    //----------------------------------------------------------------------------------------------
}
