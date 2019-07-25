package com.example.jfs_proyecto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jfs_proyecto.Adaptadores.BusquedaAdapter;
import com.example.jfs_proyecto.Adaptadores.EmpleadoAdapter;
import com.example.jfs_proyecto.Pojos.Empleado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity {

    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Empleado> lista;
    private RecyclerView recycler;
    String id;
    String profesion, sueldo, edad, estatura, nacionalidad, estado, estudios, segundo, tercer, discapacidad;
    int ImportanciaProfesion, ImportanciaSueldo, ImportanciaEstatura, ImportanciaEdad, ImportanciaNacionalidad, ImportanciaEstado, ImportanciaEstudios, ImportanciaSegundo, ImportanciaTercer, ImportanciaDiscapacidad;
    float total = 0, porcentaje;
    int importancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        profesion = getIntent().getExtras().getString("profesion");
        ImportanciaProfesion = getIntent().getExtras().getInt("ImportanciaProfesion");
        sueldo = getIntent().getExtras().getString("sueldo");
        ImportanciaSueldo = getIntent().getExtras().getInt("ImportanciaSueldo");
        edad = getIntent().getExtras().getString("edad");
        ImportanciaEdad = getIntent().getExtras().getInt("ImportanciaEdad");
        estatura = getIntent().getExtras().getString("estatura");
        ImportanciaEstatura = getIntent().getExtras().getInt("ImportanciaEstatura");
        nacionalidad = getIntent().getExtras().getString("nacionalidad");
        ImportanciaNacionalidad = getIntent().getExtras().getInt("ImportanciaNacionalidad");
        estado = getIntent().getExtras().getString("estado");
        ImportanciaEstado = getIntent().getExtras().getInt("ImportanciaEstado");
        estudios = getIntent().getExtras().getString("estudios");
        ImportanciaEstudios = getIntent().getExtras().getInt("ImportanciaEstudios");
        segundo = getIntent().getExtras().getString("segundo");
        ImportanciaSegundo = getIntent().getExtras().getInt("ImportanciaSegundo");
        tercer = getIntent().getExtras().getString("tercer");
        ImportanciaTercer = getIntent().getExtras().getInt("ImportanciaTercer");
        discapacidad = getIntent().getExtras().getString("discapacidad");
        ImportanciaDiscapacidad = getIntent().getExtras().getInt("ImportanciaDiscapacidad");
        importancia = getIntent().getExtras().getInt("total");


        lista = new ArrayList<Empleado>();
        obtenerResultado();
    }

    //Funcion para obtener la lista de resultados
    public void obtenerResultado() {
        JSON_URL = "http://jfsproyecto.online/resultados_empleador.php";
        ArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        total = 0;
                        jsonObject = response.getJSONObject(i);
                        Empleado empleado = new Empleado();
                        empleado.setNombre(jsonObject.getString("nombre"));
                        empleado.setId(jsonObject.getString("id"));
                        empleado.setImagen(jsonObject.getString("imagen"));
                        empleado.setCorreo(jsonObject.getString("correo"));
                        empleado.setTelefono(jsonObject.getString("telefono"));
                        empleado.setDireccion(jsonObject.getString("direccion"));
                        empleado.setEdad(jsonObject.getString("edad"));
                        if (jsonObject.getString("edad").equals(edad)) {
                            total += ImportanciaEdad;
                        }
                        empleado.setEstatura(jsonObject.getString("estatura"));
                        if (jsonObject.getString("estatura").equals(estatura)) {
                            total += ImportanciaEstatura;
                        }
                        empleado.setProfesion(jsonObject.getString("profesion"));
                        if (jsonObject.getString("profesion").equals(profesion)) {
                            total += ImportanciaProfesion;
                        }
                        empleado.setIngreso(jsonObject.getString("ingreso_deseado"));
                        if (jsonObject.getString("ingreso_deseado").equals(sueldo)) {
                            total += ImportanciaSueldo;
                        }
                        empleado.setEstado_civil(jsonObject.getString("estado_civil"));
                        if (jsonObject.getString("estado_civil").equals(estado)) {
                            total += ImportanciaEstado;
                        }
                        empleado.setNacionalidad(jsonObject.getString("nacionalidad"));
                        if (jsonObject.getString("nacionalidad").equals(nacionalidad)) {
                            total += ImportanciaNacionalidad;
                        }
                        empleado.setSegundo_idioma(jsonObject.getString("segundo_idioma"));
                        if (jsonObject.getString("segundo_idioma").equals(segundo)) {
                            total += ImportanciaSegundo;
                        }
                        empleado.setTercer_idioma(jsonObject.getString("tercer_idioma"));
                        if (jsonObject.getString("tercer_idioma").equals(tercer)) {
                            total += ImportanciaTercer;
                        }
                        empleado.setDiscapacidad(jsonObject.getString("discapacidad"));
                        if (jsonObject.getString("discapacidad").equals(discapacidad)) {
                            total += ImportanciaDiscapacidad;
                        }
                        empleado.setEstudios(jsonObject.getString("nivel_estudios"));
                        if (jsonObject.getString("nivel_estudios").equals(estudios)) {
                            total += ImportanciaEstudios;
                        }
                        porcentaje = (total / importancia) * 100;
                        empleado.setPorcentaje(porcentaje);

                        if (porcentaje >= 50) {
                            lista.add(empleado);
                        }

                    } catch (JSONException e) {
                        e.getCause();
                    }

                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(Busqueda.this);
                    myBuild.setMessage("No hay resultados por arriba de la media");
                    myBuild.setTitle("JFS");
                    myBuild.setCancelable(false);
                    myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = myBuild.create();
                    dialog.show();
                }

                setuprecyclerview(lista);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Busqueda.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(Busqueda.this);
        requestQueue.add(ArrayRequest);

    }

    //Funcion para configurar el RecyclerView
    public void setuprecyclerview(List<Empleado> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_busqueda);
        BusquedaAdapter myadapter = new BusquedaAdapter(this, lista, id);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

}
