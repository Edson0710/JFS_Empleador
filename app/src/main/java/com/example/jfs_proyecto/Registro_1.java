package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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

import java.util.regex.Pattern;

public class Registro_1 extends AppCompatActivity {
    EditText correo_etxt, contra1_etxt, contra2_etxt, nombre_etxt, giro_etxt, direccion_etxt, telefono_etxt;
    String correo, contra1, contra2, nombre, giro, direccion, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_1);

        correo_etxt = findViewById(R.id.Registro1_etxt_correo);
        contra1_etxt = findViewById(R.id.Registro1_etxt_contrasena);
        contra2_etxt = findViewById(R.id.Registro1_etxt_contrasena2);
        nombre_etxt = findViewById(R.id.Registro1_etxt_nombre);
        giro_etxt = findViewById(R.id.Registro1_etxt_giro);
        direccion_etxt = findViewById(R.id.Registro1_etxt_direccion);
        telefono_etxt = findViewById(R.id.Registro1_etxt_telefono);
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
        contra1 = contra1_etxt.getText().toString().trim();
        contra2 = contra2_etxt.getText().toString().trim();
        nombre = nombre_etxt.getText().toString().trim();
        giro = giro_etxt.getText().toString().trim();
        direccion = direccion_etxt.getText().toString().trim();
        telefono = telefono_etxt.getText().toString().trim();


       if (correo.equals("") || contra1.equals("") || contra2.equals("") || nombre.equals("") || giro.equals("")
                || direccion.equals("") || telefono.equals("")) {
            Toast.makeText(Registro_1.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!validarEmail(correo)) {
            Toast.makeText(this, "El correo no es válido", Toast.LENGTH_SHORT).show();
        } else if (contra1.equals(contra2)) {
            if (telefono.length() == 10) {
                validacion();
            } else {
                Toast.makeText(this, "Ingrese un número telefónico de 10 digitos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Registro_1.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    //---------------------------Funciones----------------------------------------------------------

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    //Metodo para validar si existe el correo
    public void validacion() {
        String url = "http://jfsproyecto.online/validacion_empleador.php?correo=" + correo;
        final Intent intent = new Intent(Registro_1.this, codigo.class);
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
                                            intent.putExtra("nombre", nombre);
                                            intent.putExtra("giro", giro);
                                            intent.putExtra("correo", correo);
                                            intent.putExtra("contra", contra1);
                                            intent.putExtra("direccion", direccion);
                                            intent.putExtra("telefono", telefono);
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
                       // Toast.makeText(Registro_1.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Registro_1.this);
        x.add(peticion);
    }
}
