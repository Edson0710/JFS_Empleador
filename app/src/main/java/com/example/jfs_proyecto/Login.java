package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

//----------------------------Botones---------------------------------------------------------------

    //Metodo boton registrarse
    public void Registrarse (View view){

        Intent registrarse = new Intent(Login.this, Registro_1.class);
        startActivity(registrarse);
    }

    //Boton iniciar sesion
    public void Iniciar (View view){

        Intent iniciar = new Intent(Login.this,Vista_principal.class);
        startActivity(iniciar);
    }


}
