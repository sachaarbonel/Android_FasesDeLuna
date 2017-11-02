package com.journaldev.navigationdrawer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.journaldev.navigationdrawer.R;
import com.journaldev.navigationdrawer.activities.DetalleFaseActivity;
import com.journaldev.navigationdrawer.activities.MainActivity;
import com.journaldev.navigationdrawer.adapters.FasesLunaresAdapter;
import com.journaldev.navigationdrawer.models.FaseLunar;

import java.util.ArrayList;

/**
 * Created by sacha on 04/10/2017.
 */
public class GridFragment extends Fragment {

/*    public GridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        return rootView;
    }*/

    private ArrayList<FaseLunar> mList;
    private MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mFragment = inflater.inflate(R.layout.fragment_grid, null);
        GridView mLista = (GridView) mFragment.findViewById(R.id.gridView1);
        mLista.setAdapter(getFasesLunaresAdapter());
        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View child, int position, long id) {
                //Problem here with detalleFase
                Intent newActivity = new Intent(getActivity(), DetalleFaseActivity.class);
                newActivity.putExtra("detalleFase", "hola");
                //newActivity.putExtra("detalleFase", mList.get(position).getDescripcion());
                startActivity(newActivity);
            }
        });

        return mFragment;
    }

    private FasesLunaresAdapter getFasesLunaresAdapter() {

        mList = getFaseLunarItems();
        return new FasesLunaresAdapter(mList, getActivity(), FasesLunaresAdapter.ADAPTER_MODE_GRIDVIEW);
    }

    private ArrayList<FaseLunar> getFaseLunarItems() {
        return mActivity.getFaseLunarItems();
    }

}
