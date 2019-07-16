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
import com.example.jfs_proyecto.Adaptadores.EmpleadoAdapter;
import com.example.jfs_proyecto.Adaptadores.OfertasAdapter;
import com.example.jfs_proyecto.Pojos.Empleado;
import com.example.jfs_proyecto.Pojos.Oferta;
import com.example.jfs_proyecto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Postulantes extends AppCompatActivity {
    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Empleado> lista;
    private RecyclerView recycler;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulantes);
        id = getIntent().getExtras().getString("id");
        lista = new ArrayList<Empleado>();
        obtenerPostulantes();
    }

    //Funcion para obtener la lista de ofertas
    public void obtenerPostulantes() {
        JSON_URL = "http://jfsproyecto.online/postulantes_empleador.php?id=" + id;
        ArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Empleado empleado = new Empleado();
                        empleado.setNombre(jsonObject.getString("nombre"));
                        empleado.setId(jsonObject.getString("id"));
                        empleado.setImagen(jsonObject.getString("imagen"));
                        empleado.setCorreo(jsonObject.getString("correo"));
                        empleado.setTelefono(jsonObject.getString("telefono"));
                        empleado.setEdad(jsonObject.getString("edad"));
                        empleado.setEstatura(jsonObject.getString("estatura"));
                        empleado.setDireccion(jsonObject.getString("direccion"));
                        empleado.setProfesion(jsonObject.getString("profesion"));
                        empleado.setIngreso(jsonObject.getString("ingreso_deseado"));
                        lista.add(empleado);


                    } catch (JSONException e) {
                        e.getCause();
                    }


                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(Postulantes.this);
                    myBuild.setMessage("Aun no hay postulantes para esta oferta");
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
                Toast.makeText(Postulantes.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(Postulantes.this);
        requestQueue.add(ArrayRequest);

    }

    //Funcion para configurar el RecyclerView
    public void setuprecyclerview(List<Empleado> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_postulantes);
        EmpleadoAdapter myadapter = new EmpleadoAdapter(this, lista, id);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Postulantes.this, Lista_ofertas.class);
        startActivity(intent);
    }
}
