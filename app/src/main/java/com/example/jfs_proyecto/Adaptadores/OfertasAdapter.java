package com.example.jfs_proyecto.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jfs_proyecto.Pojos.Comentario;
import com.example.jfs_proyecto.Pojos.Oferta;
import com.example.jfs_proyecto.R;

import java.util.List;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {

    private Context mContext;
    private List<Oferta> mData;

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
    public void onBindViewHolder(OfertasAdapter.MyViewHolder holder, int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nombre = itemView.findViewById(R.id.tv_nombre);

        }

    }


}