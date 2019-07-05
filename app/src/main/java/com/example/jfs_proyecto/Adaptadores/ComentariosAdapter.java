package com.example.jfs_proyecto.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.jfs_proyecto.Pojos.Comentario;
import com.example.jfs_proyecto.R;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Comentario> mData;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_comentarios.setText(mData.get(position).getComentario());
        holder.tv_calificacion.setText("Calificacion: "+mData.get(position).getCalificacion());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_comentarios;
        TextView tv_calificacion;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_calificacion = itemView.findViewById(R.id.tv_calificacion);
            tv_comentarios = itemView.findViewById(R.id.tv_comentario);

        }

    }


}