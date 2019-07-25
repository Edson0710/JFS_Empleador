package com.example.jfs_proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Informacion extends AppCompatActivity {
    TextView tv_nombre, tv_correo, tv_telefono, tv_edad, tv_estatura, tv_direccion, tv_profesion, tv_ingreso, tv_estadocivil, tv_nacionalidad, tv_segundo_idioma, tv_tercer_idioma, tv_discapacidad, tv_estudios;
    CircleImageView imageView;
    String nombre, correo, imagen, telefono, edad, estatura, direccion, profesion, ingreso, estado_civil, nacionalidad, segundo_idioma, tercer_idioma, discapacidad, estudios;

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
        tv_estadocivil = findViewById(R.id.estado_civil);
        tv_nacionalidad = findViewById(R.id.nacionalidad);
        tv_segundo_idioma = findViewById(R.id.segundo_idioma);
        tv_tercer_idioma = findViewById(R.id.tercer_idioma);
        tv_discapacidad = findViewById(R.id.discapacidad);
        tv_estudios = findViewById(R.id.nivel_estudios);

        nombre = getIntent().getExtras().getString("nombre");
        correo = getIntent().getExtras().getString("correo");
        imagen = getIntent().getExtras().getString("imagen");
        telefono = getIntent().getExtras().getString("telefono");
        edad = getIntent().getExtras().getString("edad");
        estatura = getIntent().getExtras().getString("estatura");
        direccion = getIntent().getExtras().getString("direccion");
        profesion = getIntent().getExtras().getString("profesion");
        ingreso = getIntent().getExtras().getString("ingreso");
        estado_civil = getIntent().getExtras().getString("estado_civil");
        nacionalidad = getIntent().getExtras().getString("nacionalidad");
        segundo_idioma = getIntent().getExtras().getString("segundo_idioma");
        tercer_idioma = getIntent().getExtras().getString("tercer_idioma");
        discapacidad = getIntent().getExtras().getString("discapacidad");
        estudios = getIntent().getExtras().getString("nivel_estudios");

        revisar();

        tv_nombre.setText("Nombre:\n" + nombre);
        tv_correo.setText("Correo:\n" + correo);
        Glide.with(Informacion.this).load(imagen).into(imageView);
        tv_telefono.setText("Teléfono: " + telefono);
        tv_edad.setText("Edad: " + edad + " años");
        tv_estatura.setText("Estatura: " + estatura + " cm");
        tv_direccion.setText("Dirección: " + direccion);
        tv_profesion.setText("Profesion: " + profesion);
        tv_ingreso.setText("Ingreso deseado: $" + ingreso);
        tv_estadocivil.setText("Estado civil: " + estado_civil);
        tv_nacionalidad.setText("Nacionalidad: " + nacionalidad);
        tv_segundo_idioma.setText("Segundo idioma: " + segundo_idioma);
        tv_tercer_idioma.setText("Tercer idioma: " + tercer_idioma);
        tv_discapacidad.setText("Discapacidad: " + discapacidad);
        tv_estudios.setText("Nivel de estudios: " + estudios);
    }

    public void revisar(){
        //Estado civil
        if (estado_civil.equals("1")){estado_civil="Soltero";}
        if (estado_civil.equals("2")){estado_civil="Divorciado";}
        if (estado_civil.equals("3")){estado_civil="Viudo";}
        if (estado_civil.equals("4")){estado_civil="Casado";}
        if (estado_civil.equals("5")){estado_civil="Union libre";}
        //Idiomas 2
        if (segundo_idioma.equals("1")){segundo_idioma="Ingles";}
        if (segundo_idioma.equals("2")){segundo_idioma="Chino";}
        if (segundo_idioma.equals("3")){segundo_idioma="Frances";}
        if (segundo_idioma.equals("4")){segundo_idioma="Aleman";}
        if (segundo_idioma.equals("5")){segundo_idioma="Italiano";}
        if (segundo_idioma.equals("6")){segundo_idioma="Ruso";}
        if (segundo_idioma.equals("7")){segundo_idioma="Japones";}
        if (segundo_idioma.equals("8")){segundo_idioma="Portugues";}
        if (segundo_idioma.equals("9")){segundo_idioma="Otro";}
        if (segundo_idioma.equals("10")){segundo_idioma="Ninguno";}
        //Idiomas 3
        if (tercer_idioma.equals("1")){tercer_idioma="Ingles";}
        if (tercer_idioma.equals("2")){tercer_idioma="Chino";}
        if (tercer_idioma.equals("3")){tercer_idioma="Frances";}
        if (tercer_idioma.equals("4")){tercer_idioma="Aleman";}
        if (tercer_idioma.equals("5")){tercer_idioma="Italiano";}
        if (tercer_idioma.equals("6")){tercer_idioma="Ruso";}
        if (tercer_idioma.equals("7")){tercer_idioma="Japones";}
        if (tercer_idioma.equals("8")){tercer_idioma="Portugues";}
        if (tercer_idioma.equals("9")){tercer_idioma="Otro";}
        if (tercer_idioma.equals("10")){tercer_idioma="Ninguno";}
        //Discapacidad
        if (discapacidad.equals("1")){discapacidad="Auditiva";}
        if (discapacidad.equals("2")){discapacidad="Mental Intelectual";}
        if (discapacidad.equals("3")){discapacidad="Mental Psicosocial";}
        if (discapacidad.equals("4")){discapacidad="Motriz";}
        if (discapacidad.equals("5")){discapacidad="Verbal";}
        if (discapacidad.equals("6")){discapacidad="Visual";}
        if (discapacidad.equals("7")){discapacidad="Ninguno";}
        //Estudios
        if (estudios.equals("1")){estudios="Preescolar";}
        if (estudios.equals("2")){estudios="Primaria";}
        if (estudios.equals("3")){estudios="Secundaria";}
        if (estudios.equals("4")){estudios="Bachillerato general";}
        if (estudios.equals("5")){estudios="Bachillerato tecnologico";}
        if (estudios.equals("6")){estudios="Profesional tecnico";}
        if (estudios.equals("7")){estudios="Licenciatura";}
        if (estudios.equals("8")){estudios="Maestria";}
        if (estudios.equals("9")){estudios="Doctorado";}
        if (estudios.equals("10")){estudios="Otro";}
        //Nacionalidad
        if (nacionalidad.equals("1")){nacionalidad="Aleman";}
        if (nacionalidad.equals("2")){nacionalidad="Argentino";}
        if (nacionalidad.equals("3")){nacionalidad="Brasileño";}
        if (nacionalidad.equals("4")){nacionalidad="Canadiense";}
        if (nacionalidad.equals("5")){nacionalidad="Chileno";}
        if (nacionalidad.equals("6")){nacionalidad="Chino";}
        if (nacionalidad.equals("7")){nacionalidad="Español";}
        if (nacionalidad.equals("8")){nacionalidad="Estadounidense";}
        if (nacionalidad.equals("9")){nacionalidad="Frances";}
        if (nacionalidad.equals("10")){nacionalidad="Indio";}
        if (nacionalidad.equals("11")){nacionalidad="Italiano";}
        if (nacionalidad.equals("12")){nacionalidad="Japones";}
        if (nacionalidad.equals("13")){nacionalidad="Mexicano";}
        if (nacionalidad.equals("14")){nacionalidad="Ruso";}
        if (nacionalidad.equals("15")){nacionalidad="Otro";}



    }
}
