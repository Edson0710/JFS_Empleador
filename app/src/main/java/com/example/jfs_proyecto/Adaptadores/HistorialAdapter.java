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
import com.example.jfs_proyecto.Editar_oferta;
import com.example.jfs_proyecto.Historial;
import com.example.jfs_proyecto.Pojos.Oferta;
import com.example.jfs_proyecto.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.MyViewHolder> {

    private Context mContext;
    private List<Oferta> mData;
    String nombre, id, puesto, profesion, sueldo, edad, estatura;

    public HistorialAdapter(Context mContext, List<Oferta> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public HistorialAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.historial_item, null);

        final HistorialAdapter.MyViewHolder viewHolder = new HistorialAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HistorialAdapter.MyViewHolder holder, final int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());

        holder.activar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                mensaje();

            }
        });

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                puesto = mData.get(holder.getAdapterPosition()).getPuesto();
                profesion = mData.get(holder.getAdapterPosition()).getProfesion();
                sueldo = mData.get(holder.getAdapterPosition()).getSueldo();
                edad = mData.get(holder.getAdapterPosition()).getEdad();
                estatura = mData.get(holder.getAdapterPosition()).getEstatura();
                Intent intent = new Intent(mContext, Editar_oferta.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("id", id);
                intent.putExtra("puesto", puesto);
                intent.putExtra("profesion", profesion);
                intent.putExtra("sueldo", sueldo);
                intent.putExtra("edad", edad);
                intent.putExtra("estatura", estatura);
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
        Button activar, editar;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            activar = itemView.findViewById(R.id.activar);
            editar = itemView.findViewById(R.id.editar);

        }

    }

    public void mensaje(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(mContext  );
        myBuild.setMessage("¿Estás seguro de que quieres publicar '" + nombre + "'?" );
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activar();
                Toast.makeText(mContext, "Se ha publicado la oferta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Historial.class);
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

    public void activar(){
        String url = "http://jfsproyecto.online/actualizarOferta_empleador.php?id=" + id + "&activo=" + 1;

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