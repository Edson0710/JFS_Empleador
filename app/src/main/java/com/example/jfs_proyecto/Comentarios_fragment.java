package com.example.jfs_proyecto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jfs_proyecto.Adaptadores.ComentariosAdapter;
import com.example.jfs_proyecto.Pojos.Comentario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Comentarios_fragment extends Fragment {

    String id;
    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Comentario> lista;
    private RecyclerView recycler;
    ComentariosAdapter myadapter = new ComentariosAdapter(getContext(), lista);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_comentarios, container, false);
        lista = new ArrayList<Comentario>();
        recycler = view.findViewById(R.id.recyclerview_comentarios);

        obtenerComentarios();

        return view;
    }

//----------------------Funciones-----------------------------------------------------------------------
    // Funcion para obtener los comentarios del usuario
    public void obtenerComentarios() {
        id = obtenerId();
        JSON_URL = "http://jfsproyecto.online/comentarios_empleador.php?id=" + id;

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
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
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
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ArrayRequest);

    }

    //Funcion para configurar el RecyclerView
    public void setuprecyclerview(List<Comentario> lista) {
        ComentariosAdapter myadapter = new ComentariosAdapter(getContext(), lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

    //Funcion para obtener ID
    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

}
