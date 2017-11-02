package com.journaldev.navigationdrawer.activities;


import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.journaldev.navigationdrawer.R;
import com.journaldev.navigationdrawer.adapters.DrawerItemCustomAdapter;
import com.journaldev.navigationdrawer.fragments.GridFragment;
import com.journaldev.navigationdrawer.fragments.ListFragment;
import com.journaldev.navigationdrawer.fragments.WikiFragment;
import com.journaldev.navigationdrawer.models.DataModel;
import com.journaldev.navigationdrawer.models.FaseLunar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity {

    private String mDrawableName;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private ArrayList<FaseLunar> mFaseLunar;
    private int resID;
    private CharSequence mTitle;
    private String mAlias;
    private String mFase;
    private String mDescription;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.list, "List");
        drawerItem[1] = new DataModel(R.drawable.grid, "Grid");
        drawerItem[2] = new DataModel(R.drawable.wiki, "Wiki");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
        setupDrawerToggle();


    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new ListFragment();
                break;
            case 1:
                fragment = new GridFragment();
                break;
            case 2:
                fragment = new WikiFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    public ArrayList<FaseLunar> getFaseLunarItems() {
        mFaseLunar = new ArrayList<FaseLunar>();
        //Method without JSON
       /* mFaseLunar.add(new FaseLunar(R.drawable.luna_nueva, "Luna nueva", "Novilunio", "Luna Nueva o Novilunio, también llamada \"Luna Nueva Astronómica\" o \"Luna Negra\", corresponde a la Luna Nueva Verdadera; esta fase de la Luna normalmente es imposible verla a simple vista ya que se encuentra oculta tras el resplandor solar, sólo es posible observarla cuando ocurre un eclipse total de Sol, los cuales acontecen durante esta fase lunar sólo cuando las condiciones dadas son las adecuadas."));
        mFaseLunar.add(new FaseLunar(R.drawable.creciente_iluminante, "Creciente iluminante", null, "Luna Creciente, también llamada Nueva Visible, corresponde a la Luna Nueva Tradicional y es la primera aparición de la Luna en el cielo, 18 o 30 horas después de haberse producido la posición de \"Luna Nueva Astronómica\". Esta fase de la Luna se podrá ver en el cielo hacia el oeste, una vez ya ocultado el Sol, justo por encima del crepúsculo aún restante. Tiene forma de pequeña guadaña o cuerno. Esta fase de la Luna es la que se utiliza para dar comienzo al primer día de cada mes lunar."));
        mFaseLunar.add(new FaseLunar(R.drawable.cuarto_creciente, "Cuarto creciente", "Primer cuarto", "Cuarto Creciente. Tiene su salida en el horizonte por el este a las 12 del mediodía (hora astronómica local, no necesariamente hora oficial), su cenit se produce a las 6 de la tarde y su ocaso a las 12 de la medianoche. La parte luminosa de la Luna durante esta fase tiene la forma de un círculo partido justo a la mitad (semi-círculo)."));
        mFaseLunar.add(new FaseLunar(R.drawable.gibosa_iluminante, "Gibosa iluminante", null, "Luna Gibosa Creciente, una vez ya pasada la fase del Cuarto Creciente, la Luna va tomando progresivamente día tras día, una forma convexa por ambos lados en su parte luminosa, perdiendo ese lado recto que poseía durante la fase Cuarto Creciente."));
        mFaseLunar.add(new FaseLunar(R.drawable.luna_llena, "Luna llena", "Plenilunio", "Luna Llena o Plenilunio, es cuando la concavidad de la parte luminosa de la Luna se logra completar en su totalidad hasta formar un círculo. Su salida en el horizonte es aproximadamente a las 6:00 p.m., el cenit lo alcanza aproximadamente durante la medianoche y se oculta cerca de las 6:00 de la mañana. La Luna Llena viene a marcar justo lo que es la mitad del mes lunar (14 días, 18 horas, 21 minutos 36 segundos)."));
        mFaseLunar.add(new FaseLunar(R.drawable.gibosa_menguante, "Gibosa menguante", null, "Luna Gibosa Menguante, pasada ya la fase correspondiente a la Luna Llena, la parte luminosa de la Luna comenzará a menguar con el correr de los días, tomando así de nuevo —igual como en la Luna Gibosa creciente— una apariencia de una Luna-Cóncava (gibosa) esta vez en su fase decreciente."));
        mFaseLunar.add(new FaseLunar(R.drawable.cuarto_menguante, "Cuarto menguante", "Último cuarto", "Cuarto Menguante, exactamente igual que el Cuarto Creciente, pero en sentido contrario. Además, tiene su salida en el horizonte a las 12 de la medianoche, alcanza el cenit en el cielo a las 6 de la mañana y su ocaso se produce a las 12 del mediodía, es decir, esta fase lunar corresponde al periodo de días durante el cual es posible observar a la Luna en el cielo durante las horas de la mañana."));
        mFaseLunar.add(new FaseLunar(R.drawable.creciente_menguante, "Creciente menguante", null, "Luna Menguante, conocida también como \"Creciente Menguante\" o \"Luna Vieja\" (este último término poco conocido) ya que es idéntica a la Luna Nueva Visible, pero en sentido opuesto. La Luna Menguante sólo es posible verla de madrugada, hacia el Este, justo por encima de la Aurora o Alba y antes de que salga el Sol. Tiene apariencia de pequeña guadaña."));
*/

       //Method with JSON
       try {
           JSONObject data = new JSONObject(loadJSONFromAsset());
           JSONArray faselunar = data.getJSONArray("faseLunar");
           for (int i = 0; i < faselunar.length(); i++) {
               JSONObject object = faselunar.getJSONObject(i);
               mDrawableName = object.getString("id");
               resID = getResources().getIdentifier(mDrawableName,"drawable",getPackageName());
               mDescription = object.getString("descripcion");
               mAlias = object.getString("alias");
               mFase = object.getString("fase");
               FaseLunar FL = new FaseLunar(resID,mFase,mAlias,mDescription);
               mFaseLunar.add(FL);
           }
       } catch(JSONException e){
            e.printStackTrace();
        }
        return mFaseLunar;
    }//getFaseLunarItems

    //JSON Handler Best Practice
    private String loadJSONFromAsset(){
        String json = null;
        AssetManager assetManager = getAssets();
        try{
            InputStream IS = assetManager.open("datosFases.json");
            int size = IS.available();
            byte[] buffer = new byte[size];
            IS.read(buffer);
            IS.close();
            json = new String(buffer,"UTF-8");

        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

        return json;
    }



}
