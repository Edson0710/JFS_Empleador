package com.example.jfs_proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


public class Inicio_fragment extends Fragment {

    CircleImageView circleImageView;
    TextView tv_calificacion;
    String imagen, calificacion="1", id;
    Button nueva_oferta, ver_oferta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio_fragment, container, false);

        circleImageView = view.findViewById(R.id.inicio_imagen);
        nueva_oferta = view.findViewById(R.id.nueva_oferta);
        ver_oferta = view.findViewById(R.id.nuevos_postulantes);
        tv_calificacion = view.findViewById(R.id.calificacion);
        calificacion();
        imagen = obtenerUrl();
        Picasso.with(getContext()).load(imagen).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(circleImageView);


        //--------------------------------Botones---------------------------------------------------------------------
        nueva_oferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Nueva_oferta.class);
                startActivity(intent);
            }
        });

        ver_oferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Lista_ofertas.class);
                startActivity(intent);
            }
        });

//-------------------------------------------------------------------------------------------------------------
        return view;
    }

    //--------------------------------Funciones---------------------------------------------------------------------
    //Funcion para obtener Imagen
    public String obtenerUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("IMAGEN", "1");
        return id_preference;
    }

    //Funcion para obtener ID
    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    public void calificacion() {
        id = obtenerId();
        String url = "http://jfsproyecto.online/calificacion_empleador.php?id=" + id;
        JsonObjectRequest peticion = new JsonObjectRequest
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
                                        case "EXITOSO":
                                            calificacion = response.getString("calificacion");
                                            tv_calificacion.setText("Calificación: " + calificacion);
                                            break;
                                        case "FALLIDO":
                                            Toast.makeText(getContext(), "Fallo", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(getContext());
        x.add(peticion);
    }
}
