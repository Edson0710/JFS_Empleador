package com.example.jfs_proyecto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Vista_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);


//------------------------------------- navigation drawer------------------------------------------

        Toolbar toolbar = findViewById(R.id.Toolbar);
        //setSupportActionBar(toolbar);

        drawer = findViewById(R.id.Drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
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
                Intent CerrarSesion = new Intent(Vista_principal.this, Login.class);
                startActivity(CerrarSesion);
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
//-------------------------------------------------------------------------------------------------
}
