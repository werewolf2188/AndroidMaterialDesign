package com.example.presentacion.example;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.presentacion.Utils;
import com.example.presentacion.fragments.TercerFragment;
import com.example.presentacion.fragments.PrimerFragment;
import com.example.presentacion.fragments.SegundoFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ListAdapter.ClickListener {

    public Toolbar tbBar;

    private PrimerFragment primerFragment;
    private SegundoFragment segundoFragment;
    private TercerFragment tercerFragment;

    private FrameLayout contenedor;
    public ActionBarDrawerToggle mDrawerToggle;
    public DrawerLayout mDrawerLayout;
    private RecyclerView Lista;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter adapter;
    LinearLayout linearLayout;
    Utils utils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        tbBar = (Toolbar) findViewById(R.id.tbBar);
        contenedor = (FrameLayout) findViewById(R.id.flContenedor);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        //RecyclerView

        Lista = (RecyclerView) findViewById(R.id.rvLista);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ListAdapter(this, getData());
        adapter.setClickListener(this);
        Lista.setAdapter(adapter);
        Lista.setLayoutManager(layoutManager);

        linearLayout=(LinearLayout)findViewById(R.id.llBaner);
        utils=new Utils();
        utils.mostrarBanner(this,linearLayout);

        setSupportActionBar(tbBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, tbBar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        if (savedInstanceState == null && contenedor != null)
            setPrimerFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                break;
            case R.id.menu2:
                setSegundoFragment();
                break;
            case R.id.menu3:
                startActivity(new Intent(MainActivity.this, Actividad2.class));
                break;
            case R.id.menu4:
                break;
            case R.id.menuSettings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setPrimerFragment() {
        primerFragment = new PrimerFragment();
        setFragment(primerFragment, false);
    }

    public void setSegundoFragment() {
        segundoFragment = new SegundoFragment();
        setFragment(segundoFragment, true);

    }

    public void setTercerFragment() {
        tercerFragment = new TercerFragment();
        setFragment(tercerFragment, false);
    }

    public void setFragment(Fragment fragment, Boolean isBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContenedor, fragment);
        if (isBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public List<DataMenu> getData() {
        List<DataMenu> data = new ArrayList<>();
        int[] iconos = {android.R.drawable.ic_menu_today, android.R.drawable.ic_menu_send, android.R.drawable.ic_menu_help, android.R.drawable.ic_menu_zoom,
                android.R.drawable.ic_menu_more, android.R.drawable.ic_menu_camera, android.R.drawable.ic_media_play, android.R.drawable.ic_menu_month};
        String[] titulos = {"Today", "Email", "Help", "Zoom", "More", "Camera", "Play", "Month"};

        for (int i = 0; i < iconos.length && i < titulos.length; i++) {
            DataMenu current = new DataMenu();
            current.imagenID = iconos[i];
            current.Nombre = titulos[i];
            data.add(current);
        }
        return data;

    }

    @Override
    public void itemClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, Actividad2.class));
                cerrarMenu();
                break;
            case 1:
                setPrimerFragment();
                cerrarMenu();
                break;
            case 2:
                setTercerFragment();
                cerrarMenu();
                break;
            case 3:
                setPrimerFragment();
                cerrarMenu();
                break;
        }
    }

    private void cerrarMenu() {
        mDrawerLayout.closeDrawers();
    }
}
