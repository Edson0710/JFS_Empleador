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

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;


public class Inicio_fragment extends Fragment {

    CircleImageView circleImageView;
    String imagen;
    Button nueva_oferta, ver_oferta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio_fragment, container, false);

        circleImageView = view.findViewById(R.id.inicio_imagen);
        nueva_oferta = view.findViewById(R.id.nueva_oferta);
        ver_oferta = view.findViewById(R.id.nuevos_postulantes);

        imagen = obtenerUrl();
        Glide.with(getContext()).load(imagen).into(circleImageView);

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
        return  view;
    }

//--------------------------------Funciones---------------------------------------------------------------------
    //Funcion para obtener Imagen
    public String obtenerUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("IMAGEN", "1");
        return id_preference;
    }
}
