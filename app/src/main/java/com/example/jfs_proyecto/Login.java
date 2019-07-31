package com.example.jfs_proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Login extends AppCompatActivity {
    EditText correo_etxt, pass_etxt;
    RadioButton sesion;
    String correo, pass;
    private boolean isActivate;
    private static final String STRING_PREFERENCES = "preferencias";
    private static final String PREFERENCE_ESTADO_BUTTON = "estado.button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (obtenerEstado()) {
            int tipo = obtenerTipo();
            if (tipo==0) {
                Intent intent = new Intent(Login.this, Vista_principal.class);
                startActivity(intent);
            }
            if (tipo==1) {
                Intent intent = new Intent(Login.this, Admin.class);
                startActivity(intent);
            }
        }

        correo_etxt = findViewById(R.id.Login_etxt_correo);
        pass_etxt = findViewById(R.id.Login_etxt_contrasena);
        sesion = findViewById(R.id.Login_rboton_sesion);

        isActivate = sesion.isChecked(); //DESACTIVADO

        sesion.setOnClickListener(new View.OnClickListener() {
            //ACTIVADO
            @Override
            public void onClick(View v) {
                if (isActivate) {
                    sesion.setChecked(false);
                }
                isActivate = sesion.isChecked();

            }
        });

    }

//----------------------------Botones---------------------------------------------------------------

    //Metodo boton registrarse
    public void Registrarse(View view) {

        Intent intent = new Intent(Login.this, Registro_1.class);
        startActivity(intent);
    }

    //Boton iniciar sesion
    public void Iniciar(View view) {
        guardarEstado();
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
        String url = "http://jfsproyecto.online/login_empleador.php?correo=" + correo +
                "&pass=" + pass;
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
                                            String id = response.getString("id");
                                            String nombre = response.getString("nombre");
                                            String imagenUrl = response.getString("imagen");
                                            int tipo = response.getInt("tipo");
                                            guardarId(id);
                                            guardarNombre(nombre);
                                            guardarUrl(imagenUrl);
                                            guardarTipo(tipo);
                                            if (tipo==0) {
                                                Intent intent = new Intent(Login.this, Vista_principal.class);
                                                intent.putExtra("imagenUrl", imagenUrl);
                                                startActivity(intent);
                                            }
                                            if (tipo==1) {
                                                Intent intent = new Intent(Login.this, Admin.class);
                                                startActivity(intent);
                                            }
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

    //Funcion para guardar el ID
    public void guardarId(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("ID", my_id);
        myEditor.commit();
    }

    //Funcion para guardar el nombre
    public void guardarNombre(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("NOMBRE", my_id);
        myEditor.commit();
    }

    //Funcion para guardar la imagen
    public void guardarUrl(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("IMAGEN", my_id);
        myEditor.commit();
    }

    //Funcion para guardar la imagen
    public void guardarTipo(int my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putInt("TIPO", my_id);
        myEditor.commit();
    }

    //Funcion para no poder regresar al activity anterior
    @Override
    public void onBackPressed() {
    }

    //Funcion para cambiar el estado al cerrar sesion
    public static void changeEstado(Context c, boolean b) {
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON, b).apply();
    }

    //Funcion para guardar el estado de mantener sesion
    public void guardarEstado() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON, sesion.isChecked()).apply();
    }

    //Funcion para obtener si se mantiene sesion
    public boolean obtenerEstado() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON, false);
    }

    public int obtenerTipo() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        int id_preference = preferences.getInt("TIPO", 1);
        return id_preference;
    }
}
