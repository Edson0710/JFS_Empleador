package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Editar_oferta extends AppCompatActivity {
    Spinner EstadoCivil_opciones, Discapacidades_opciones, SegundoIdioma_opciones, TercerIdioma_opciones, NivelEstudios_opciones, Nacionalidad_opciones;
    String nombre, id, puesto, profesion, sueldo, edad, estatura;
    String EstadoCivil, NivelEstudios, SegundoIdioma, Tercer_idioma, Discapacidades, Nacionalidad;
    EditText et_nombre, et_puesto, et_profesion, et_sueldo, et_edad, et_estatura;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_oferta);

        nombre = getIntent().getExtras().getString("nombre");
        puesto = getIntent().getExtras().getString("puesto");
        profesion = getIntent().getExtras().getString("profesion");
        sueldo = getIntent().getExtras().getString("sueldo");
        edad = getIntent().getExtras().getString("edad");
        estatura = getIntent().getExtras().getString("estatura");
        id = getIntent().getExtras().getString("id");

        et_nombre = findViewById(R.id.nombre);
        et_puesto = findViewById(R.id.puesto);
        et_profesion = findViewById(R.id.profesion);
        et_sueldo = findViewById(R.id.sueldo);
        et_edad = findViewById(R.id.edad);
        et_estatura = findViewById(R.id.estatura);
        guardar = findViewById(R.id.aceptar);

        et_nombre.setText(nombre);
        et_puesto.setText(puesto);
        et_profesion.setText(profesion);
        et_sueldo.setText(sueldo);
        et_edad.setText(edad);
        et_estatura.setText(estatura);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
                Intent intent = new Intent(Editar_oferta.this, Historial.class);
                finish();
                startActivity(intent);
            }
        });


        //-----------------------------------------------------------------------------------------------------------------//

        Nacionalidad_opciones = findViewById(R.id.Spinner_Nacionalidad);

        ArrayAdapter<CharSequence> adapter0 = ArrayAdapter.createFromResource
                (Editar_oferta.this, R.array.Nacionalidad_opciones, android.R.layout.simple_spinner_item);
        Nacionalidad_opciones.setAdapter(adapter0);
        Nacionalidad_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Nacionalidad = Nacionalidad_opciones.getSelectedItem().toString();
                if (Nacionalidad.equals("Aleman")) {
                    Nacionalidad = "1";
                } else if (Nacionalidad.equals("Argentino")) {
                    Nacionalidad = "2";
                } else if (Nacionalidad.equals("Brasileño")) {
                    Nacionalidad = "3";
                } else if (Nacionalidad.equals("Canadiense")) {
                    Nacionalidad = "4";
                } else if (Nacionalidad.equals("Chileno")) {
                    Nacionalidad = "5";
                } else if (Nacionalidad.equals("Chino")) {
                    Nacionalidad = "6";
                } else if (Nacionalidad.equals("Español")) {
                    Nacionalidad = "7";
                } else if (Nacionalidad.equals("Estadounidense")) {
                    Nacionalidad = "8";
                } else if (Nacionalidad.equals("Frances")) {
                    Nacionalidad = "9";
                } else if (Nacionalidad.equals("Indio")) {
                    Nacionalidad = "10";
                } else if (Nacionalidad.equals("Italiano")) {
                    Nacionalidad = "11";
                } else if (Nacionalidad.equals("Japones")) {
                    Nacionalidad = "12";
                } else if (Nacionalidad.equals("Mexicano")) {
                    Nacionalidad = "13";
                } else if (Nacionalidad.equals("Ruso")) {
                    Nacionalidad = "14";
                } else if (Nacionalidad.equals("Otro")) {
                    Nacionalidad = "15";
                } else {
                    Nacionalidad = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//


//-----------------------------------------------------------------------------------------------------------------//

        EstadoCivil_opciones = findViewById(R.id.Spinner_EstadoCivil);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (Editar_oferta.this, R.array.EstadoCivil_opciones, android.R.layout.simple_spinner_item);
        EstadoCivil_opciones.setAdapter(adapter);
        EstadoCivil_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EstadoCivil = EstadoCivil_opciones.getSelectedItem().toString();
                if (EstadoCivil.equals("Soltero")) {
                    EstadoCivil = "1";
                } else if (EstadoCivil.equals("Divorciado")) {
                    EstadoCivil = "2";
                } else if (EstadoCivil.equals("Viudo")) {
                    EstadoCivil = "3";
                } else if (EstadoCivil.equals("Casado")) {
                    EstadoCivil = "4";
                } else if (EstadoCivil.equals("Union libre")) {
                    EstadoCivil = "5";
                } else {
                    EstadoCivil = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //-----------------------------------------------------------------------------------------------------------------//

        Discapacidades_opciones = findViewById(R.id.Spinner_Discapacidades);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (Editar_oferta.this, R.array.Discapacidades_opciones, android.R.layout.simple_spinner_item);
        Discapacidades_opciones.setAdapter(adapter1);
        Discapacidades_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Discapacidades = Discapacidades_opciones.getSelectedItem().toString();
                if (Discapacidades.equals("Auditiva")) {
                    Discapacidades = "1";
                } else if (Discapacidades.equals("Mental Intelectual")) {
                    Discapacidades = "2";
                } else if (Discapacidades.equals("Mental Psicosocial")) {
                    Discapacidades = "3";
                } else if (Discapacidades.equals("Motriz")) {
                    Discapacidades = "4";
                } else if (Discapacidades.equals("Verbal")) {
                    Discapacidades = "5";
                } else if (Discapacidades.equals("Visual")) {
                    Discapacidades = "6";
                } else if (Discapacidades.equals("Ninguna")) {
                    Discapacidades = "7";
                } else {
                    Discapacidades = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        SegundoIdioma_opciones = findViewById(R.id.Spinner_SegundoIdioma);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource
                (Editar_oferta.this, R.array.SegundoIdioma_opciones, android.R.layout.simple_spinner_item);
        SegundoIdioma_opciones.setAdapter(adapter2);
        SegundoIdioma_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SegundoIdioma = SegundoIdioma_opciones.getSelectedItem().toString();
                if (SegundoIdioma.equals("Ingles")) {
                    SegundoIdioma = "1";
                } else if (SegundoIdioma.equals("Chino")) {
                    SegundoIdioma = "2";
                } else if (SegundoIdioma.equals("Frances")) {
                    SegundoIdioma = "3";
                } else if (SegundoIdioma.equals("Aleman")) {
                    SegundoIdioma = "4";
                } else if (SegundoIdioma.equals("Italiano")) {
                    SegundoIdioma = "5";
                } else if (SegundoIdioma.equals("Ruso")) {
                    SegundoIdioma = "6";
                } else if (SegundoIdioma.equals("Japones")) {
                    SegundoIdioma = "7";
                } else if (SegundoIdioma.equals("Portugues")) {
                    SegundoIdioma = "8";
                } else if (SegundoIdioma.equals("Otro")) {
                    SegundoIdioma = "9";
                } else if (SegundoIdioma.equals("Ninguno")) {
                    SegundoIdioma = "10";
                } else {
                    SegundoIdioma = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        TercerIdioma_opciones = findViewById(R.id.Spinner_TercerIdioma);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource
                (Editar_oferta.this, R.array.TercerIdioma_opciones, android.R.layout.simple_spinner_item);
        TercerIdioma_opciones.setAdapter(adapter3);
        TercerIdioma_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tercer_idioma = TercerIdioma_opciones.getSelectedItem().toString();
                if (Tercer_idioma.equals("Ingles")) {
                    Tercer_idioma = "1";
                } else if (Tercer_idioma.equals("Chino")) {
                    Tercer_idioma = "2";
                } else if (Tercer_idioma.equals("Frances")) {
                    Tercer_idioma = "3";
                } else if (Tercer_idioma.equals("Aleman")) {
                    Tercer_idioma = "4";
                } else if (Tercer_idioma.equals("Italiano")) {
                    Tercer_idioma = "5";
                } else if (Tercer_idioma.equals("Ruso")) {
                    Tercer_idioma = "6";
                } else if (Tercer_idioma.equals("Japones")) {
                    Tercer_idioma = "7";
                } else if (Tercer_idioma.equals("Portugues")) {
                    Tercer_idioma = "8";
                } else if (Tercer_idioma.equals("Otro")) {
                    Tercer_idioma = "9";
                } else if (Tercer_idioma.equals("Ninguno")) {
                    Tercer_idioma = "10";
                } else {
                    Tercer_idioma = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        NivelEstudios_opciones = findViewById(R.id.Spinner_estudios);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource
                (Editar_oferta.this, R.array.NivelEstudios_opciones, android.R.layout.simple_spinner_item);
        NivelEstudios_opciones.setAdapter(adapter4);
        NivelEstudios_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NivelEstudios = NivelEstudios_opciones.getSelectedItem().toString();
                if (NivelEstudios.equals("Preescolar")) {
                    NivelEstudios = "1";
                } else if (NivelEstudios.equals("Primaria")) {
                    NivelEstudios = "2";
                } else if (NivelEstudios.equals("Secundaria")) {
                    NivelEstudios = "3";
                } else if (NivelEstudios.equals("Bachillerato general")) {
                    NivelEstudios = "4";
                } else if (NivelEstudios.equals("Bachillerato tecnologico")) {
                    NivelEstudios = "5";
                } else if (NivelEstudios.equals("Profesional tecnico")) {
                    NivelEstudios = "6";
                } else if (NivelEstudios.equals("Licenciatura")) {
                    NivelEstudios = "7";
                } else if (NivelEstudios.equals("Maestria")) {
                    NivelEstudios = "8";
                } else if (NivelEstudios.equals("Doctorado")) {
                    NivelEstudios = "9";
                } else if (NivelEstudios.equals("Otro")) {
                    NivelEstudios = "10";
                } else {
                    NivelEstudios = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void guardarCambios(){
        nombre = et_nombre.getText().toString().trim();
        puesto = et_puesto.getText().toString().trim();
        profesion = et_profesion.getText().toString().trim();
        sueldo = et_sueldo.getText().toString().trim();
        edad = et_edad.getText().toString().trim();
        estatura = et_estatura.getText().toString().trim();
        String url = null;
        try {
            url = "http://jfsproyecto.online/editarOferta_empleador.php?nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&id=" + id
                    + "&puesto=" + URLEncoder.encode(puesto, "UTF-8") + "&profesion=" + URLEncoder.encode(profesion,"UTF-8")
                    + "&sueldo=" + sueldo + "&edad=" + edad + "&estatura=" + estatura + "&nacionalidad=" + Nacionalidad
                    + "&estado=" + EstadoCivil + "&segundo=" + SegundoIdioma + "&tercer=" + Tercer_idioma + "&discapacidad=" + Discapacidades
                    + "&estudios=" + NivelEstudios;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final JsonObjectRequest peticion = new JsonObjectRequest
                (
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String valor = response.getString("Estado");

                                    switch (valor) {
                                        case "NO":

                                            break;
                                        case "SI":
                                            break;
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(Crear_oferta.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Editar_oferta.this);
        x.add(peticion);
    }
}
