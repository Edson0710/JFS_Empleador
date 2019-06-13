package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Registro_1 extends AppCompatActivity {
    EditText correo_etxt;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_1);

        correo_etxt = findViewById(R.id.Registro1_etxt_correo);
    }

    //-------------------------Botones--------------------------------------------------------------
    //Metodo boton cancelar
    public void Cancelar(View view) {

        Intent intent = new Intent(Registro_1.this, Login.class);
        startActivity(intent);
    }

    //Metodo boton continuar
    public void Continuar(View view) {
        correo = correo_etxt.getText().toString().trim();
        if (correo.equals("")) {
            Toast.makeText(Registro_1.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            validacion();
        }
    }

    //---------------------------Funciones----------------------------------------------------------
    //Metodo para validar si existe el correo
    public void validacion() {
        String url = "http://192.168.100.80/jfs_proyecto/validacion_empleador.php?correo=" + correo;
        final Intent intent = new Intent(Registro_1.this, Registro_2.class);
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
                                        case "NO":
                                            startActivity(intent);
                                            break;
                                        case "SI":
                                            Toast.makeText(Registro_1.this, "Este correo ya ha sido registrado", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Registro_1.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Registro_1.this);
        x.add(peticion);
    }
}
