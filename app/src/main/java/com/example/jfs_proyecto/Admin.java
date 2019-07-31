package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    Button sesion, reportados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sesion = findViewById(R.id.sesion);
        reportados = findViewById(R.id.comentarios);

        reportados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Reportados.class);
                startActivity(intent);
            }
        });

        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.changeEstado(getApplicationContext(), false);
                Intent intent = new Intent(Admin.this, Login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
