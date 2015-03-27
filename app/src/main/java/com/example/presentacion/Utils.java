package com.example.presentacion;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Created by Becari on 26/03/2015.
 */
public class Utils {
    private AdView adView;

    private final static String BANNER_ID = "ca-app-pub-9210775914357058/9065580324";
    public boolean isBanner;

    public void mostrarBanner(Context contex, LinearLayout layout) {
        adView = new AdView(contex);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(BANNER_ID);

        layout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void ocultarBanner(LinearLayout layout) {
        layout.setVisibility(View.INVISIBLE);
    }
}
