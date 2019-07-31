package com.example.jfs_proyecto.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.example.jfs_proyecto.Pojos.Comentario;
import com.example.jfs_proyecto.R;
import com.example.jfs_proyecto.Reportados;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ReportadosAdapter extends RecyclerView.Adapter<ReportadosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Comentario> mData;
    String id, nombre, comentario;

    public ReportadosAdapter(Context mContext, List<Comentario> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public ReportadosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.reportado_item, null);

        final ReportadosAdapter.MyViewHolder viewHolder = new ReportadosAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReportadosAdapter.MyViewHolder holder, final int position) {

        holder.tv_comentarios.setText(mData.get(position).getComentario());
        holder.tv_calificacion.setText("Calificacion: " + mData.get(position).getCalificacion());

        holder.reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = mData.get(position).getId();
                mensaje();

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
            reportar = itemView.findViewById(R.id.borrar);

        }

    }


    public void mensaje() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(mContext);
        myBuild.setMessage("¿Estás seguro de que quieres eliminar este comentario?");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminar();
                Toast.makeText(mContext, "Comentario eliminado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Reportados.class);
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

    public void eliminar() {
        String url = "http://jfsproyecto.online/borrarComentario_empleador.php?id=" + id ;

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

