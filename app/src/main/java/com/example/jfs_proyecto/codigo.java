package com.example.jfs_proyecto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class codigo extends AppCompatActivity {
    String nombre, giro, correo, contra, direccion, telefono;
    EditText validar;
    int dato;
    String cod;
    Button comprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);
        validar = findViewById(R.id.validar);
        comprobar = findViewById(R.id.comprobar);

        nombre = getIntent().getExtras().getString("nombre");
        giro = getIntent().getExtras().getString("giro");
        correo = getIntent().getExtras().getString("correo");
        contra = getIntent().getExtras().getString("contra");
        direccion = getIntent().getExtras().getString("direccion");
        telefono = getIntent().getExtras().getString("telefono");

        Random r = new Random();
        final int numero = r.nextInt(1111 - 1) + 7777;

        validacion(numero, correo);
        mensaje();

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cod = validar.getText().toString().trim();
                dato = Integer.parseInt(cod);
                if (dato == numero) {
                    Intent intent = new Intent(codigo.this, Registro_2.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("giro", giro);
                    intent.putExtra("correo", correo);
                    intent.putExtra("contra", contra);
                    intent.putExtra("direccion", direccion);
                    intent.putExtra("telefono", telefono);
                    startActivity(intent);
                } else {
                    Toast.makeText(codigo.this, "El codigo no coincide", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validacion(int codigo, String correo) {
        String url = "http://jfsproyecto.online/enviarCodigo.php?correo=" + correo + "&codigo=" + codigo;
        final Intent intent = new Intent(codigo.this, codigo.class);
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

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(codigo.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(codigo.this);
        x.add(peticion);
    }

    public void mensaje(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(codigo.this);
        myBuild.setMessage("Un código será enviado al correo proporcionado para verificar que el correo exista.\n" +
                "En caso de cancelar tu registro, el código enviado no será válido.");
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

}
