package com.journaldev.navigationdrawer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.journaldev.navigationdrawer.R;

/**
 * Created by sacha on 04/10/2017.
 */
public class WikiFragment extends Fragment {

    /*public WikiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wiki, container, false);

        return rootView;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Como el layout de este fragmento es muy sencillo lo crearemos por c�digo.
        WebView wv = new WebView(getActivity());
        wv.loadUrl("https://es.m.wikipedia.org/wiki/Fase_lunar");

        // Este c�digo hace que los links se carguen en el mismo webview y no
        // se abran en el browser nativo
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return wv;
    }
}
