package com.example.jfs_proyecto.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jfs_proyecto.Crear_oferta;
import com.example.jfs_proyecto.Historial;
import com.example.jfs_proyecto.Lista_ofertas;
import com.example.jfs_proyecto.Pojos.Oferta;
import com.example.jfs_proyecto.Postulantes;
import com.example.jfs_proyecto.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {

    private Context mContext;
    private List<Oferta> mData;
    String nombre, id;

    public OfertasAdapter(Context mContext, List<Oferta> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public OfertasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.oferta_item, null);

        final OfertasAdapter.MyViewHolder viewHolder = new OfertasAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final OfertasAdapter.MyViewHolder holder, final int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());

        holder.desactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                mensaje();

            }
        });

        holder.postulantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = mData.get(holder.getAdapterPosition()).getId();
                Intent intent = new Intent(mContext, Postulantes.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre;
        Button desactivar, postulantes;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            desactivar = itemView.findViewById(R.id.desactivar);
            postulantes = itemView.findViewById(R.id.postulantes);

        }

    }

    public void mensaje(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(mContext  );
        myBuild.setMessage("¿Estás seguro de que quieres desactivar '" + nombre + "'?" );
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                desactivar();
                Toast.makeText(mContext, "Se ha desactivado la oferta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Lista_ofertas.class);
                mContext.startActivity(intent);
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

    public void desactivar(){
        String url = "http://jfsproyecto.online/actualizarOferta_empleador.php?id=" + id + "&activo=" + 0;

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

                                            break;
                                        case "SI":
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
                        //Toast.makeText(Crear_oferta.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(mContext);
        x.add(peticion);
    }


}