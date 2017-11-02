package com.journaldev.navigationdrawer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.journaldev.navigationdrawer.R;
import com.journaldev.navigationdrawer.activities.DetalleFaseActivity;
import com.journaldev.navigationdrawer.activities.MainActivity;
import com.journaldev.navigationdrawer.adapters.FasesLunaresAdapter;
import com.journaldev.navigationdrawer.models.FaseLunar;

import java.util.ArrayList;
import 	android.widget.AdapterView;

/**
 * Created by sacha on 04/10/2017.
 */
public class ListFragment extends Fragment {

  /*  public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

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

        View mFragment = inflater.inflate(R.layout.fragment_list, null);
        ListView mLista = (ListView) mFragment.findViewById(R.id.listView1);
        mLista.setAdapter(getFasesLunaresAdapter());

        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View child, int position, long id) {
                Intent newActivity = new Intent(getActivity(), DetalleFaseActivity.class);

                // newActivity.putExtra("detalleFase", mList.get(position).getDescripcion());
                //startActivity(newActivity);
                /*newActivity.putExtra("detalleFase",  mList.get(position).getDescripcion());
                getActivity().setResult(DetalleFaseActivity.RESULT_OK,newActivity);
                getActivity().finish();*/

                newActivity.putExtra("detalleFase","hola");
                startActivityForResult(newActivity, 1);
            }
        });

        return mFragment;
    }

    private FasesLunaresAdapter getFasesLunaresAdapter() {

        mList = getFaseLunarItems();
        return new FasesLunaresAdapter(mList, getActivity(), FasesLunaresAdapter.ADAPTER_MODE_LISTVIEW);
    }

    private ArrayList<FaseLunar> getFaseLunarItems() {
        return mActivity.getFaseLunarItems();
    }

}
