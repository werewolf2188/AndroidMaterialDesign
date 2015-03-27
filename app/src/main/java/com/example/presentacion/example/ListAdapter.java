package com.example.presentacion.example;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Becari on 24/03/2015.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<DataMenu> lista_data = Collections.emptyList();
    private LayoutInflater inflater;
    Context context;
    private ClickListener clickListener;

    public ListAdapter(Context context, List<DataMenu> lista) {
        inflater = LayoutInflater.from(context);
        this.lista_data = lista;
        this.context = context;
    }

    //Cuando Recycler view necesita un nuevo Reciclerview.viewholder dado el tipo que representa un item
    //se construye con una nueva vista
    //sera usado para desplegar items del adaptador usando onBindViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //v representa la vista
        View v = inflater.inflate(R.layout.fila_lista, parent, false);
        //Se crea un objeto holder
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }


    //enviaremos los datos correspondientes
    //llamado por recyclerview para mostrar los datos en la posicion especificada
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataMenu current = lista_data.get(position);
        holder.titulo.setText(current.Nombre);
        holder.imagen.setImageResource(current.imagenID);
    }

    @Override
    public int getItemCount() {
        return lista_data.size();
    }


    //esta clase sera encargada de buscar los ids de la vista que se le pasa
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titulo;
        ImageView imagen;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            imagen = (ImageView) itemView.findViewById(R.id.ivImagen);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClick(v, getPosition());
            }
        }
    }

    public interface ClickListener {
        public void itemClick(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
