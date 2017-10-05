package com.journaldev.navigationdrawer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.journaldev.navigationdrawer.R;

/**
 * Created by Sacha on 04/10/2017.
 */

public class DetalleFaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detalle);

        Intent callingIntent = getIntent();
        Bundle extras = callingIntent.getExtras();
        String descripcion = extras.getString("detalleFase");

        TextView detalleTv = (TextView) findViewById(R.id.fase_lunar_descripcion);

        detalleTv.setText(descripcion);
    }
}
