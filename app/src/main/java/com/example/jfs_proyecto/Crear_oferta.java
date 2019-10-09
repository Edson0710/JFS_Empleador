package com.example.jfs_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Crear_oferta extends AppCompatActivity {
    Spinner EstadoCivil_opciones, Discapacidades_opciones, SegundoIdioma_opciones, TercerIdioma_opciones, NivelEstudios_opciones, Nacionalidad_opciones;
    Spinner ImportanciaProfesion_opciones, ImportanciaSueldo_opciones, ImportanciaEstatura_opciones, ImportanciaEdad_opciones, ImportanciaNacionalidad_opciones, ImportanciaEstado_opciones, ImportanciaEstudios_opciones, ImportanciaSegundo_opciones, ImportanciaTercer_opciones, ImportanciaDiscapacidad_opciones;
    EditText et_nombre, et_puesto, et_profesion, et_sueldo, et_edad, et_estatura;
    Button publicar, buscar;
    String nombre, id, puesto, profesion, sueldo, edad, estatura;
    String EstadoCivil, NivelEstudios, SegundoIdioma, Tercer_idioma, Discapacidades, Nacionalidad;
    int ImportanciaProfesion, ImportanciaSueldo, ImportanciaEstatura, ImportanciaEdad, ImportanciaNacionalidad, ImportanciaEstado, ImportanciaEstudios, ImportanciaSegundo, ImportanciaTercer, ImportanciaDiscapacidad;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_oferta);
        et_nombre = findViewById(R.id.nombre);
        et_puesto = findViewById(R.id.puesto);
        et_profesion = findViewById(R.id.profesion);
        et_sueldo = findViewById(R.id.sueldo);
        et_edad = findViewById(R.id.edad);
        et_estatura = findViewById(R.id.estatura);
        publicar = findViewById(R.id.crear_publicar);
        buscar = findViewById(R.id.crear_buscar);

        id = obtenerId();

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = ImportanciaProfesion + ImportanciaSueldo + ImportanciaEstatura + ImportanciaEdad + ImportanciaNacionalidad
                        + ImportanciaEstado + ImportanciaEstudios + ImportanciaSegundo + ImportanciaTercer + ImportanciaDiscapacidad;
                profesion = et_profesion.getText().toString().trim();
                sueldo = et_sueldo.getText().toString().trim();
                edad = et_edad.getText().toString().trim();
                estatura = et_estatura.getText().toString().trim();
                Intent intent = new Intent(Crear_oferta.this, Busqueda.class);
                intent.putExtra("profesion", profesion);
                intent.putExtra("ImportanciaProfesion", ImportanciaProfesion);
                intent.putExtra("sueldo", sueldo);
                intent.putExtra("ImportanciaSueldo", ImportanciaSueldo);
                intent.putExtra("edad", edad);
                intent.putExtra("ImportanciaEdad", ImportanciaEdad);
                intent.putExtra("estatura", estatura);
                intent.putExtra("ImportanciaEstatura", ImportanciaEstatura);
                intent.putExtra("nacionalidad", Nacionalidad);
                intent.putExtra("ImportanciaNacionalidad", ImportanciaNacionalidad);
                intent.putExtra("estado", EstadoCivil);
                intent.putExtra("ImportanciaEstado", ImportanciaEstado);
                intent.putExtra("estudios", NivelEstudios);
                intent.putExtra("ImportanciaEstudios", ImportanciaEstudios);
                intent.putExtra("segundo", SegundoIdioma);
                intent.putExtra("ImportanciaSegundo", ImportanciaSegundo);
                intent.putExtra("tercer", Tercer_idioma);
                intent.putExtra("ImportanciaTercer", ImportanciaTercer);
                intent.putExtra("discapacidad", Discapacidades);
                intent.putExtra("ImportanciaDiscapacidad", ImportanciaDiscapacidad);
                intent.putExtra("total", total);
                intent.putExtra("desaparecer", 1);
                startActivity(intent);
            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaProfesion_opciones = findViewById(R.id.profesion_importancia);

        ArrayAdapter<CharSequence> adapterI1 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaProfesion_opciones.setAdapter(adapterI1);
        ImportanciaProfesion_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaProfesion = ImportanciaProfesion_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaSueldo_opciones = findViewById(R.id.sueldo_importancia);

        ArrayAdapter<CharSequence> adapterI2 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaSueldo_opciones.setAdapter(adapterI2);
        ImportanciaSueldo_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaSueldo = ImportanciaSueldo_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaEstatura_opciones = findViewById(R.id.estatura_importancia);

        ArrayAdapter<CharSequence> adapterI3 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaEstatura_opciones.setAdapter(adapterI3);
        ImportanciaEstatura_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaEstatura = ImportanciaEstatura_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaEdad_opciones = findViewById(R.id.edad_importancia);

        ArrayAdapter<CharSequence> adapterI4 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaEdad_opciones.setAdapter(adapterI4);
        ImportanciaEdad_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaEdad = ImportanciaEdad_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaNacionalidad_opciones = findViewById(R.id.nacionalidad_importancia);

        ArrayAdapter<CharSequence> adapterI5 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaNacionalidad_opciones.setAdapter(adapterI5);
        ImportanciaNacionalidad_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaNacionalidad = ImportanciaNacionalidad_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaEstado_opciones = findViewById(R.id.estado_importancia);

        ArrayAdapter<CharSequence> adapterI6 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaEstado_opciones.setAdapter(adapterI6);
        ImportanciaEstado_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaEstado = ImportanciaEstado_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaEstudios_opciones = findViewById(R.id.estudios_importancia);

        ArrayAdapter<CharSequence> adapterI7 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaEstudios_opciones.setAdapter(adapterI7);
        ImportanciaEstudios_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaEstudios = ImportanciaEstudios_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaSegundo_opciones = findViewById(R.id.segundo_importancia);

        ArrayAdapter<CharSequence> adapterI8 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaSegundo_opciones.setAdapter(adapterI8);
        ImportanciaSegundo_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaSegundo = ImportanciaSegundo_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaTercer_opciones = findViewById(R.id.tercer_importancia);

        ArrayAdapter<CharSequence> adapterI9 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaTercer_opciones.setAdapter(adapterI9);
        ImportanciaTercer_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaTercer = ImportanciaTercer_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        ImportanciaDiscapacidad_opciones = findViewById(R.id.discapacidad_importancia);

        ArrayAdapter<CharSequence> adapterI10 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Importancia_opciones, android.R.layout.simple_spinner_dropdown_item);
        ImportanciaDiscapacidad_opciones.setAdapter(adapterI10);
        ImportanciaDiscapacidad_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImportanciaDiscapacidad = ImportanciaDiscapacidad_opciones.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------------------------------------------------------------------------------------//

        Nacionalidad_opciones = findViewById(R.id.Spinner_Nacionalidad);

        ArrayAdapter<CharSequence> adapter0 = ArrayAdapter.createFromResource
                (Crear_oferta.this, R.array.Nacionalidad_opciones, android.R.layout.simple_spinner_dropdown_item);
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
                (Crear_oferta.this, R.array.EstadoCivil_opciones, android.R.layout.simple_spinner_dropdown_item);
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
                (Crear_oferta.this, R.array.Discapacidades_opciones, android.R.layout.simple_spinner_dropdown_item);
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
                (Crear_oferta.this, R.array.SegundoIdioma_opciones, android.R.layout.simple_spinner_dropdown_item);
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
                (Crear_oferta.this, R.array.TercerIdioma_opciones, android.R.layout.simple_spinner_dropdown_item);
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
                (Crear_oferta.this, R.array.NivelEstudios_opciones, android.R.layout.simple_spinner_dropdown_item);
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

    //Metodo para publicar la oferta
    public void crear() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        nombre = et_nombre.getText().toString().trim();
        puesto = et_puesto.getText().toString().trim();
        profesion = et_profesion.getText().toString().trim();
        sueldo = et_sueldo.getText().toString().trim();
        edad = et_edad.getText().toString().trim();
        estatura = et_estatura.getText().toString().trim();
        if (nombre.length() >= 3) {
            if (puesto.length() >= 3) {
                if (profesion.length() >= 3) {
                    if (nombre.equals("") || puesto.equals("") || profesion.equals("") || sueldo.equals("") || edad.equals("")
                            || estatura.equals("") || Nacionalidad.equals("0") || EstadoCivil.equals("0") || SegundoIdioma.equals("0") || Tercer_idioma.equals("0")
                            || Discapacidades.equals("0") || NivelEstudios.equals("0")) {
                        Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Integer.parseInt(edad) >= 18 && Integer.parseInt(edad) <= 99) {
                            if (Integer.parseInt(estatura) >= 130) {
                                notificacion();
                                String url = null;
                                try {
                                    url = "http://jfsproyecto.online/crearOferta_empleador.php?nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&id=" + id
                                            + "&puesto=" + URLEncoder.encode(puesto, "UTF-8") + "&profesion=" + URLEncoder.encode(profesion, "UTF-8")
                                            + "&sueldo=" + sueldo + "&edad=" + edad + "&estatura=" + estatura + "&nacionalidad=" + Nacionalidad
                                            + "&estado=" + EstadoCivil + "&segundo=" + SegundoIdioma + "&tercer=" + Tercer_idioma + "&discapacidad=" + Discapacidades
                                            + "&estudios=" + NivelEstudios + "&fecha=" + URLEncoder.encode(date, "UTF-8");
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
                                                            Intent intent = new Intent(Crear_oferta.this, Vista_principal.class);
                                                            startActivity(intent);
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
                                RequestQueue x = Volley.newRequestQueue(Crear_oferta.this);
                                x.add(peticion);
                            } else{
                                Toast.makeText(this, "La estatura debe de ser mayor a 130", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "La edad debe ser mayor o igual a 18", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "La profesion debe de tener mas de 3 caracteres", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "El puesto debe de tener más de 3 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El nombre debe de tener más de 3 caracteres", Toast.LENGTH_SHORT).show();
        }

    }

    public void notificacion() {
        puesto = et_puesto.getText().toString().trim();
        String url = null;
        try {
            url = "http://jfsproyecto.online/notificacion_ofertas.php?puesto=" + URLEncoder.encode(puesto, "UTF-8");
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
        RequestQueue x = Volley.newRequestQueue(Crear_oferta.this);
        x.add(peticion);
    }

    //Funcion para obtener ID
    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Crear_oferta.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }
}
