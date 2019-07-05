package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Nueva_oferta extends AppCompatActivity {
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_oferta);

        crear = findViewById(R.id.nueva_crear);


//--------------------Botones---------------------------------------------------------------------
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nueva_oferta.this, Crear_oferta.class);
                startActivity(intent);
            }
        });
    }
}
