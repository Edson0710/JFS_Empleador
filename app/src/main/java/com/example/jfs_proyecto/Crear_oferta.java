package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Crear_oferta extends AppCompatActivity {
    EditText et_nombre;
    Button publicar;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_oferta);
        et_nombre = findViewById(R.id.crear_nombre);
        publicar = findViewById(R.id.crear_publicar);




        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
                Intent intent = new Intent(Crear_oferta.this, Vista_principal.class);
                startActivity(intent);
            }
        });
    }

    //Metodo para publicar la oferta
    public void crear() {
        nombre = et_nombre.getText().toString().trim();
        String url = "http://jfsproyecto.online/crearOferta_empleador.php?nombre=" + nombre;
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
}
