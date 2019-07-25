package com.example.jfs_proyecto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class Perfil_fragment extends Fragment {

    String id, nombre, transporte, comision, bonos;
    EditText et_nombre, et_correo, et_giro, et_telefono, et_direccion, et_otro1, et_otro2, et_otro3;
    RadioButton transporte1, transporte2, comision1, comision2, bonos1, bonos2;
    Button guardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_perfil, container, false);

        et_nombre = view.findViewById(R.id.nombre);
        et_correo = view.findViewById(R.id.correo);
        et_giro = view.findViewById(R.id.giro);
        et_telefono = view.findViewById(R.id.telefono);
        et_direccion = view.findViewById(R.id.direccion);
        transporte1 = view.findViewById(R.id.transporte1);
        transporte2 = view.findViewById(R.id.transporte2);
        comision1 = view.findViewById(R.id.comisiones1);
        comision2 = view.findViewById(R.id.comisiones2);
        bonos1 = view.findViewById(R.id.bonos1);
        bonos2 = view.findViewById(R.id.bonos2);
        et_otro1 = view.findViewById(R.id.otro1);
        et_otro2 = view.findViewById(R.id.otro2);
        et_otro3 = view.findViewById(R.id.otro3);
        guardar = view.findViewById(R.id.guardar);
        informacion();
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje();
            }
        });

        return view;
    }

    public void informacion() {
        id = obtenerId();
        String url = "http://jfsproyecto.online/miInformacion_empleador.php?id=" + id;
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
                                            nombre = response.getString("nombre");
                                            String correo = response.getString("correo");
                                            String giro = response.getString("giro");
                                            String telefono = response.getString("telefono");
                                            String direccion = response.getString("direccion");
                                            transporte = response.getString("transporte");
                                            comision = response.getString("comision");
                                            bonos = response.getString("bonos");
                                            String otro1 = response.getString("otro1");
                                            String otro2 = response.getString("otro2");
                                            String otro3 = response.getString("otro3");
                                            et_nombre.setText(nombre);
                                            et_correo.setText(correo);
                                            et_giro.setText(giro);
                                            et_telefono.setText(telefono);
                                            et_direccion.setText(direccion);
                                            et_otro1.setText(otro1);
                                            et_otro2.setText(otro2);
                                            et_otro3.setText(otro3);
                                            if (transporte.equals("1")) {
                                                transporte1.setChecked(true);
                                            } else {
                                                transporte2.setChecked(true);
                                            }
                                            if (comision.equals("1")) {
                                                comision1.setChecked(true);
                                            } else {
                                                comision2.setChecked(true);
                                            }
                                            if (bonos.equals("1")) {
                                                bonos1.setChecked(true);
                                            } else {
                                                bonos2.setChecked(true);
                                            }

                                            break;
                                        case "FALLIDO":
                                            Toast.makeText(getContext(), "Fallo en la conexión", Toast.LENGTH_SHORT).show();
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

    public void mensaje() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
        myBuild.setMessage("¿Estás seguro de que quieres guardar los cambios?");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editar();
                Toast.makeText(getContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Vista_principal.class);
                startActivity(intent);
            }
        });
        myBuild.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }

    public void editar() {
        revisar();
        guardarNombre(et_nombre.getText().toString().trim());
        String url = null;
        try {
            url = "http://jfsproyecto.online/editarInformacion_empleador.php?id=" + id + "&nombre=" + URLEncoder.encode(et_nombre.getText().toString().trim(), "UTF-8")
                    + "&correo=" + et_correo.getText().toString().trim() + "&giro=" + et_giro.getText().toString().trim()
                    + "&telefono=" + et_telefono.getText().toString().trim() + "&direccion=" + URLEncoder.encode(et_direccion.getText().toString().trim(), "UTF-8")
                    + "&otro1=" + URLEncoder.encode(et_otro1.getText().toString().trim(), "UTF-8") + "&otro2=" + URLEncoder.encode(et_otro2.getText().toString().trim(), "UTF-8")
                    + "&otro3=" + URLEncoder.encode(et_otro3.getText().toString().trim(), "UTF-8") + "&transporte=" + transporte
                    + "&comisiones=" + comision + "&bonos=" + bonos;
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
        RequestQueue x = Volley.newRequestQueue(getContext());
        x.add(peticion);
    }

    public void revisar() {
        if (transporte1.isChecked()) {
            transporte = "1";
        } else {
            transporte = "0";
        }
        if (comision1.isChecked()) {
            comision = "1";
        } else {
            comision = "0";
        }
        if (bonos1.isChecked()) {
            bonos = "1";
        } else {
            bonos = "0";
        }
    }


    //Funcion para obtener ID
    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    //Funcion para guardar el nombre
    public void guardarNombre(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("NOMBRE", my_id);
        myEditor.commit();
    }

    //Funcion para guardar la imagen
    public void guardarUrl(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("IMAGEN", my_id);
        myEditor.commit();
    }

}
