package com.example.mojtba.myapplication;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mojtba on 08/06/17.
 */


public class StudentActivity extends AppCompatActivity {

    Button search;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main_activity);

        search=(Button)findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoInternetAlertMsg();
            }
        });
    }

    private void NoInternetAlertMsg() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(
                StudentActivity.this);

        builder.setMessage(
                "Internet is disabled,do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog,
                                                final int id) {
                                Intent intt = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                                ComponentName cn = new ComponentName(
                                        "com.android.phone",
                                        "com.android.phone.Settings");
                                intt.setComponent(cn);
                                startActivity(intt);
                            }
                        });

        final AlertDialog alert = builder.create();
        alert.setTitle("Services Reply");
        alert.setIcon(R.drawable.buttonshape);
        alert.show();
    }
}