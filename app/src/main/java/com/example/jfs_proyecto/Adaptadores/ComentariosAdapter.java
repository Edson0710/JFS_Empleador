package com.example.jfs_proyecto.Adaptadores;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.example.jfs_proyecto.Lista_ofertas;
import com.example.jfs_proyecto.Login;
import com.example.jfs_proyecto.Pojos.Comentario;
import com.example.jfs_proyecto.R;
import com.example.jfs_proyecto.Vista_principal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Comentario> mData;
    String id, nombre, comentario;

    public ComentariosAdapter(Context mContext, List<Comentario> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.comentario_item, null);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        int estado = mData.get(position).getEstado();
        if (estado == 0){
            holder.reportar.setBackgroundColor(Color.parseColor("#8bc34a"));
        } else {
            holder.reportar.setBackgroundColor(Color.parseColor("#f44336"));
        }

        holder.tv_comentarios.setText(mData.get(position).getComentario());
        holder.tv_calificacion.setText("Calificacion: " + mData.get(position).getCalificacion());

        holder.reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = mData.get(position).getId();
                nombre = obtenerNombre();
                comentario = mData.get(position).getComentario();
                int estado = mData.get(position).getEstado();
                if (estado == 0) {
                    mensaje(position);
                } else {
                    Toast.makeText(mContext, "Este comentario ya ha sido reportado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_comentarios;
        TextView tv_calificacion;
        Button reportar;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_calificacion = itemView.findViewById(R.id.tv_calificacion);
            tv_comentarios = itemView.findViewById(R.id.tv_comentario);
            reportar = itemView.findViewById(R.id.reportar);

        }

    }

    //Funcion para obtener ID
    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    //Funcion para obtener Nombre
    public String obtenerNombre() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String id_preference = preferences.getString("NOMBRE", "1");
        return id_preference;
    }

    public void mensaje(final int position) {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(mContext);
        myBuild.setMessage("¿Estás seguro de que quieres reportar este comentario?");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                correo();
                mData.get(position).setEstado(1);
                Toast.makeText(mContext, "Comentario reportado", Toast.LENGTH_SHORT).show();
            }
        });
        myBuild.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }

    public void correo() {
        String url = null;
        try {
            url = "http://jfsproyecto.online/reportar_empleador.php?id=" + id +
                    "&nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&comentario=" + URLEncoder.encode(comentario, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                                            break;
                                        case "FALLIDO":
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
                        //Toast.makeText(Login.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(mContext);
        x.add(peticion);
    }


}