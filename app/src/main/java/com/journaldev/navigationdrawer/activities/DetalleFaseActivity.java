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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Intent callingIntent = getIntent();
        setContentView(R.layout.fragment_detalle);
        TextView detalleTv = (TextView) findViewById(R.id.fase_lunar_descripcion);

        if(requestCode==1){
            if(resultCode==RESULT_OK){

                String descripcion = (String)data.getStringExtra("detalleFase");
                detalleTv.setText("hola");

                /*Bundle extras = callingIntent.getExtras();
                String descripcion = extras.getString("detalleFase");
                TextView detalleTv = (TextView) findViewById(R.id.fase_lunar_descripcion);


                //detalleTv.setText("hola");
                detalleTv.setText(descripcion);*/
            }
        }

    }

}
