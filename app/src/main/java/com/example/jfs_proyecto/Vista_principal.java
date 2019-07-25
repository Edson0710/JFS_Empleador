package com.example.jfs_proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vista_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    TextView nombre_tv, editar;
    CircleImageView imagen;
    String id, nombre, imagenUrl;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private String UPLOAD_URL = "http://jfsproyecto.online/editarImagen_empleador.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);

        id = obtenerId();
        nombre = obtenerNombre();
        imagenUrl = obtenerUrl();

        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new Inicio_fragment()).commit();



//------------------------------------- navigation drawer------------------------------------------

        Toolbar toolbar = findViewById(R.id.Toolbar);
        //setSupportActionBar(toolbar);

        drawer = findViewById(R.id.Drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        nombre_tv = hView.findViewById(R.id.Nav_header_tview_nombre);
        imagen = hView.findViewById(R.id.Nav_header_imagen_imagen);
        editar = hView.findViewById(R.id.editar);
        nombre_tv.setText(nombre);
        Picasso.with(Vista_principal.this).load(imagenUrl).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(imagen);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_inicio:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new Inicio_fragment()).commit();
                break;

            case R.id.nav_perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new Perfil_fragment()).commit();
                break;

            case R.id.nav_comentario:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new Comentarios_fragment()).commit();
                break;

            case R.id.nav_cerrar_sesion:
                Login.changeEstado(getApplicationContext(), false);
                Intent intent = new Intent(Vista_principal.this, Login.class);
                startActivity(intent);
                break;

            case R.id.nav_soporte:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new Soporte_fragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }

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
                imagen.setImageBitmap(bitmap);
                uploadImage();
                recreate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//--------------------------------------Funciones-------------------------------------------------

    private void uploadImage() {
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(getApplicationContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);


                //Creación de parámetros
                Map<String, String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put("id", id);
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


    //Funcion para obtener ID
    public String obtenerId() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Vista_principal.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    //Funcion para obtener Nombre
    public String obtenerNombre() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Vista_principal.this);
        String id_preference = preferences.getString("NOMBRE", "1");
        return id_preference;
    }

    //Funcion para obtener Imagen
    public String obtenerUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Vista_principal.this);
        String id_preference = preferences.getString("IMAGEN", "1");
        return id_preference;
    }
}
