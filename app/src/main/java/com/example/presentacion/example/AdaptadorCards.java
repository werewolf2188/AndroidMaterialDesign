package com.example.presentacion.example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Becari on 26/03/2015.
 */
public class AdaptadorCards extends RecyclerView.Adapter<AdaptadorCards.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<DataCards> listaCards;


    public AdaptadorCards(Context context, List<DataCards> lista) {
        this.context = context;
        this.listaCards = lista;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.card_lista_item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataCards dataCards= listaCards.get(position);
        holder.Titulo.setText(dataCards.Titulo);
        holder.Subtitulo.setText(dataCards.Subtitulo);
        holder.Imagen.setImageResource(dataCards.Imagen);
        holder.Nombre.setText(dataCards.Nombre);


    }

    @Override
    public int getItemCount() {
        return listaCards.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Titulo,Nombre,Subtitulo;
        ImageView Imagen;

        public MyViewHolder(View itemView) {
            super(itemView);
            Imagen=(ImageView)itemView.findViewById(R.id.ivImagen);
            Titulo=(TextView)itemView.findViewById(R.id.tvTitulo);
            Nombre=(TextView)itemView.findViewById(R.id.tvNombre);
            Subtitulo=(TextView)itemView.findViewById(R.id.tvSubtitulo);
        }
    }
}
