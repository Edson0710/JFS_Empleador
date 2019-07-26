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
import com.bumptech.glide.Glide;
import com.example.jfs_proyecto.Informacion;
import com.example.jfs_proyecto.Lista_ofertas;
import com.example.jfs_proyecto.Pojos.Empleado;
import com.example.jfs_proyecto.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusquedaAdapter extends RecyclerView.Adapter<BusquedaAdapter.MyViewHolder> {

    private Context mContext;
    private List<Empleado> mData;
    String nombre, id, correo, imagen, telefono, edad, estatura, direccion, profesion, ingreso;
    String id_oferta, estado_civil, nacionalidad, segundo_idioma, tercer_idioma, discapaciodad, estudios;

    public BusquedaAdapter(Context mContext, List<Empleado> mData, String id_oferta) {
        this.mContext = mContext;
        this.mData = mData;
        this.id_oferta = id_oferta;
    }

    @Override
    public BusquedaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.busqueda_item, null);

        final BusquedaAdapter.MyViewHolder viewHolder = new BusquedaAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BusquedaAdapter.MyViewHolder holder, final int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());
        holder.tv_porcentaje.setText("Porcentaje:" + mData.get(position).getPorcentaje() + "%");
        Glide.with(mContext).load(mData.get(position).getImagen()).into(holder.imageView);

        holder.informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                correo = mData.get(holder.getAdapterPosition()).getCorreo();
                imagen = mData.get(holder.getAdapterPosition()).getImagen();
                telefono = mData.get(holder.getAdapterPosition()).getTelefono();
                edad = mData.get(holder.getAdapterPosition()).getEdad();
                estatura = mData.get(holder.getAdapterPosition()).getEstatura();
                direccion = mData.get(holder.getAdapterPosition()).getDireccion();
                profesion = mData.get(holder.getAdapterPosition()).getProfesion();
                ingreso = mData.get(holder.getAdapterPosition()).getIngreso();
                estado_civil = mData.get(holder.getAdapterPosition()).getEstado_civil();
                nacionalidad = mData.get(holder.getAdapterPosition()).getNacionalidad();
                segundo_idioma = mData.get(holder.getAdapterPosition()).getSegundo_idioma();
                tercer_idioma = mData.get(holder.getAdapterPosition()).getTercer_idioma();
                discapaciodad = mData.get(holder.getAdapterPosition()).getDiscapacidad();
                estudios = mData.get(holder.getAdapterPosition()).getEstudios();
                Intent intent = new Intent(mContext, Informacion.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("correo", correo);
                intent.putExtra("imagen", imagen);
                intent.putExtra("telefono", telefono);
                intent.putExtra("edad", edad);
                intent.putExtra("estatura", estatura);
                intent.putExtra("direccion", direccion);
                intent.putExtra("profesion", profesion);
                intent.putExtra("ingreso", ingreso);
                intent.putExtra("estado_civil", estado_civil);
                intent.putExtra("nacionalidad", nacionalidad);
                intent.putExtra("segundo_idioma", segundo_idioma);
                intent.putExtra("tercer_idioma", tercer_idioma);
                intent.putExtra("discapacidad", discapaciodad);
                intent.putExtra("nivel_estudios", estudios);
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre, tv_porcentaje;
        Button informacion, contratar;
        CircleImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_porcentaje = itemView.findViewById(R.id.tv_porcentaje);
            imageView = itemView.findViewById(R.id.imagen);
            informacion = itemView.findViewById(R.id.informacion);
        }

    }

    public void mensaje(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(mContext  );
        myBuild.setMessage("¿Estás seguro de que quieres contratar a '" + nombre + "'?" );
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contratar();
                Toast.makeText(mContext, "Contratación realizada", Toast.LENGTH_SHORT).show();
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

    public void contratar(){
        String id_empresa = obtenerId();
        String url = "http://jfsproyecto.online/contratar_empleador.php?id=" + id_oferta + "&activo=" + 0 + "&empresa=" + id_empresa
                + "&empleado=" + id;

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

    //Funcion para obtener ID
    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }


}