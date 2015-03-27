package com.example.presentacion.example;

import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.presentacion.Utils;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;


public class Actividad2 extends ActionBarActivity {

    Toolbar tbBar;
    private ActionButton actionButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdaptadorCards adaptadorCards;
    Utils utils;
    LinearLayout llbanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        tbBar = (Toolbar) findViewById(R.id.tbBar);

        actionButton = (ActionButton) findViewById(R.id.action_button);
        recyclerView = (RecyclerView) findViewById(R.id.rvLista2);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srActualizar);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adaptadorCards = new AdaptadorCards(this, getData());
        recyclerView.setAdapter(adaptadorCards);

        utils= new Utils();
        llbanner=(LinearLayout)findViewById(R.id.llBaner2);
        utils.mostrarBanner(this,llbanner);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple),
                getResources().getColor(R.color.red), getResources().getColor(R.color.blue));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Actividad2.this, "Actualizando", Toast.LENGTH_SHORT).show();
            }
        });

        actionButton.setButtonColor(getResources().getColor(R.color.green));
        actionButton.setImageResource(R.drawable.ic_action_new);
        actionButton.setButtonColorPressed(getResources().getColor(R.color.PrimaryColor));
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //Toolbar
        setSupportActionBar(tbBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
            case R.id.action_settings:
                utils.ocultarBanner(llbanner);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<DataCards> getData() {
        List<DataCards> data = new ArrayList<>();
        int[] imagenes = {R.drawable.paisaje_agua, R.drawable.paisaje_pasto, R.drawable.fondo_android, R.drawable.logo_fact
                , R.drawable.paisaje_agua, R.drawable.paisaje_pasto};
        String[] titulos = {"Titulo 1", "Titulo 2", "Titulo 3", "Titulo 4", "Titulo 5", "Titulo 6"};
        String[] subtitulos = {"Subtitulo 1", "Subtitulo 2", "Subtitulo 3", "Subtitulo 4", "Subtitulo 5", "Subtitulo 6"};
        String[] nombre = {"Lugar Uno", "Lugar Dos", "Lugar Tres", "Lugar Cuatro", "Lugar Cinco", "Lugar Seis"};

        for (int i = 0; i < imagenes.length && i < titulos.length && i < subtitulos.length && i < nombre.length; i++) {
            DataCards dataCards = new DataCards();
            dataCards.Imagen=imagenes[i];
            dataCards.Nombre=nombre[i];
            dataCards.Titulo=titulos[i];
            dataCards.Subtitulo=subtitulos[i];
            data.add(dataCards);
        }
        return data;

    }


}
