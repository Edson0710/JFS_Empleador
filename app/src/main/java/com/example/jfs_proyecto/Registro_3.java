package com.example.jfs_proyecto;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registro_3 extends AppCompatActivity {
    String nombre, giro, correo, contra, direccion, telefono, otro1, otro2, otro3, transporte, comisiones, bonos;
    CircleImageView imagen;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private String UPLOAD_URL = "http://jfsproyecto.online/registro_empleador.php";
    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_3);

        imagen = findViewById(R.id.Registro3_imagen_imagen);

        nombre = getIntent().getExtras().getString("nombre");
        giro = getIntent().getExtras().getString("giro");
        correo = getIntent().getExtras().getString("correo");
        contra = getIntent().getExtras().getString("contra");
        direccion = getIntent().getExtras().getString("direccion");
        telefono = getIntent().getExtras().getString("telefono");
        otro1 = getIntent().getExtras().getString("otro1");
        otro2 = getIntent().getExtras().getString("otro2");
        otro3 = getIntent().getExtras().getString("otro3");
        transporte = getIntent().getExtras().getString("transporte");
        comisiones = getIntent().getExtras().getString("comisiones");
        bonos = getIntent().getExtras().getString("bonos");


        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

    }

    //-------------------------Botones--------------------------------------------------------------
    //Metodo boton regresar
    public void Regresar (View view){
        Intent intent = new Intent(Registro_3.this, Registro_2.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("giro", giro);
        intent.putExtra("correo", correo);
        intent.putExtra("contra", contra);
        intent.putExtra("direccion", direccion);
        intent.putExtra("telefono", telefono);
        startActivity(intent);
    }


    //Metodo boton terminar registro
    public void Terminar (View view){
        uploadImage();
        if (flag){
            mensaje();
        } else {
            //Intent intent = new Intent(Registro_3.this, Login.class);
            //startActivity(intent);
        }
    }

    //----------------------------------------------------------------------------------------------

    //---------------------------Imagen-------------------------------------------------------------

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                bitmap = redimensionarImagen(bitmap, 600, 800);
                imagen.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {
        int ancho = bitmap.getWidth();
        int alto = bitmap.getHeight();

        if (ancho > anchoNuevo || alto > altoNuevo) {
            float escalaAncha =  anchoNuevo/ancho;
            float escalaAlto = altoNuevo/alto;
            Matrix matrix = new Matrix();
            matrix.postScale(escalaAncha, escalaAlto);
            return Bitmap.createBitmap(bitmap, 0, 0, ancho, alto, matrix, false);

        } else {
            return bitmap;
        }

    }


    //---------------------------Funciones-------------------------------------------------

    private void uploadImage() {
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this, "Registrando...", "Espere por favor...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        flag = true;
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        mensaje();
                        //Mostrando el mensaje de la respuesta

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        mensaje();
                        //Showing toast
                        Toast.makeText(Registro_3.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        flag = true;
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);


                //Creación de parámetros
                Map<String, String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put("nombre", nombre);
                params.put("giro", giro);
                params.put("correo", correo);
                params.put("contra", contra);
                params.put("direccion", direccion);
                params.put("telefono", telefono);
                params.put("otro1", otro1);
                params.put("otro2", otro2);
                params.put("otro3", otro3);
                params.put("transporte", transporte);
                params.put("comisiones", comisiones);
                params.put("bonos", bonos);
                params.put("imagen", imagen);
                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }

    public void mensaje(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(Registro_3.this);
        myBuild.setMessage("Bienvenido a JFS, tu registro ha sido exitoso.");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Registro_3.this, Login.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }


}
