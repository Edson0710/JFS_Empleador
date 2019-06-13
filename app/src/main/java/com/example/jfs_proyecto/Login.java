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

public class Login extends AppCompatActivity {
    EditText correo_etxt, pass_etxt;
    String correo, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo_etxt = findViewById(R.id.Login_etxt_correo);
        pass_etxt = findViewById(R.id.Login_etxt_contrasena);

    }

//----------------------------Botones---------------------------------------------------------------

    //Metodo boton registrarse
    public void Registrarse(View view) {

        Intent intent = new Intent(Login.this, Registro_1.class);
        startActivity(intent);
    }

    //Boton iniciar sesion
    public void Iniciar(View view) {
        correo = correo_etxt.getText().toString().trim();
        pass = pass_etxt.getText().toString().trim();
        if (correo.equals("") || pass.equals("")) {
            Toast.makeText(Login.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            login();
        }
    }

    //-----------------------------Funciones--------------------------------------------------------
    //Funcion para login
    public void login() {
        String url = "http://192.168.100.80/jfs_proyecto/login_empleador.php?correo=" + correo +
                "&pass=" + pass;
        final Intent intent = new Intent(Login.this, Vista_principal.class);
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
                                            startActivity(intent);
                                            break;
                                        case "FALLIDO":
                                            Toast.makeText(Login.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Login.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Login.this);
        x.add(peticion);
    }

}
