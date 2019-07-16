package com.example.jfs_proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Informacion extends AppCompatActivity {
    TextView tv_nombre, tv_correo, tv_telefono, tv_edad, tv_estatura, tv_direccion, tv_profesion, tv_ingreso;
    CircleImageView imageView;
    String nombre, correo, imagen, telefono, edad, estatura, direccion, profesion, ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        tv_nombre = findViewById(R.id.nombre);
        tv_correo = findViewById(R.id.correo);
        imageView = findViewById(R.id.imagen);
        tv_telefono = findViewById(R.id.telefono);
        tv_edad = findViewById(R.id.edad);
        tv_estatura = findViewById(R.id.estatura);
        tv_direccion = findViewById(R.id.direccion);
        tv_profesion = findViewById(R.id.profesion);
        tv_ingreso = findViewById(R.id.ingreso);

        nombre = getIntent().getExtras().getString("nombre");
        correo = getIntent().getExtras().getString("correo");
        imagen = getIntent().getExtras().getString("imagen");
        telefono = getIntent().getExtras().getString("telefono");
        edad = getIntent().getExtras().getString("edad");
        estatura = getIntent().getExtras().getString("estatura");
        direccion = getIntent().getExtras().getString("direccion");
        profesion = getIntent().getExtras().getString("profesion");
        ingreso = getIntent().getExtras().getString("ingreso");

        tv_nombre.setText("Nombre:\n" + nombre);
        tv_correo.setText("Correo:\n" + correo);
        Glide.with(Informacion.this).load(imagen).into(imageView);
        tv_telefono.setText("Teléfono: " + telefono);
        tv_edad.setText("Edad: " + edad + " años");
        tv_estatura.setText("Estatura: " + estatura + " cm");
        tv_direccion.setText("Dirección: " + direccion);
        tv_profesion.setText("Profesion: " + profesion);
        tv_ingreso.setText("Ingreso deseado: $" + ingreso);

    }
}
