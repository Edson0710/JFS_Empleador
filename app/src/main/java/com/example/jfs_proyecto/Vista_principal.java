package com.example.jfs_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vista_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    TextView nombre_tv;
    CircleImageView imagen;
    String id, nombre, imagenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);

        id = obtenerId();
        nombre = obtenerNombre();
        imagenUrl = obtenerUrl();



//------------------------------------- navigation drawer------------------------------------------

        Toolbar toolbar = findViewById(R.id.Toolbar);
        //setSupportActionBar(toolbar);

        drawer = findViewById(R.id.Drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        nombre_tv = hView.findViewById(R.id.Nav_header_tview_nombre);
        imagen = hView.findViewById(R.id.Nav_header_imagen_imagen);
        nombre_tv.setText(nombre);
        Glide.with(getApplicationContext()).load(imagenUrl).into(imagen);
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
            super.onBackPressed();
        }
    }
//--------------------------------------Funciones-------------------------------------------------

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
