package com.journaldev.navigationdrawer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.journaldev.navigationdrawer.R;

/**
 * Created by sacha on 04/10/2017.
 */
public class WikiFragment extends Fragment {

    public WikiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wiki, container, false);

        return rootView;
    }

}
