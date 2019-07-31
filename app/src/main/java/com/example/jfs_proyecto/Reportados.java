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
import com.example.jfs_proyecto.Adaptadores.ComentariosAdapter;
import com.example.jfs_proyecto.Adaptadores.ReportadosAdapter;
import com.example.jfs_proyecto.Pojos.Comentario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Reportados extends AppCompatActivity {

    String id;
    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Comentario> lista;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportados);

        lista = new ArrayList<Comentario>();
        obtenerComentarios();
    }

    public void obtenerComentarios() {
        JSON_URL = "http://jfsproyecto.online/reportados_empleador.php";

        ArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Comentario comentario = new Comentario();
                        comentario.setComentario(jsonObject.getString("comentario"));
                        comentario.setCalificacion(jsonObject.getString("calificacion"));
                        comentario.setId(jsonObject.getString("id"));

                        lista.add(comentario);


                    } catch (JSONException e) {
                        e.getCause();
                    }


                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(Reportados.this);
                    myBuild.setMessage("Este usuario aún no tiene comentarios");
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
                Toast.makeText(Reportados.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(Reportados.this);
        requestQueue.add(ArrayRequest);

    }

    //Funcion para configurar el RecyclerView
    public void setuprecyclerview(List<Comentario> lista) {
        recycler = findViewById(R.id.recyclerview_reportados);
        ReportadosAdapter myadapter = new ReportadosAdapter(Reportados.this, lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Reportados.this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Reportados.this, Admin.class);
        startActivity(intent);
    }
}
