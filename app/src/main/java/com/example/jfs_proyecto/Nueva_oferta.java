package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Nueva_oferta extends AppCompatActivity {
    Button crear, historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_oferta);

        crear = findViewById(R.id.nueva_crear);
        historial = findViewById(R.id.nueva_historial);


//--------------------Botones---------------------------------------------------------------------
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nueva_oferta.this, Crear_oferta.class);
                startActivity(intent);
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nueva_oferta.this, Historial.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Nueva_oferta.this, Vista_principal.class);
        startActivity(intent);
    }
}
