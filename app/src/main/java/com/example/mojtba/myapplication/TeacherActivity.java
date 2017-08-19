package com.example.mojtba.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mojtba on 09/06/17.
 */
class Global {
    public static String coursename;
}

public class TeacherActivity extends AppCompatActivity {
    Button addCourse , start;
    EditText name;

    WifiApManager wifiApManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main_activity);
        addCourse=(Button)findViewById(R.id.add);
        name=(EditText)findViewById(R.id.CourseName);
        start=(Button)findViewById(R.id.startLec);
        wifiApManager = new WifiApManager(getApplicationContext());
        Global.coursename = String.valueOf(name.getText());


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiApManager.setWifiApEnabled(null, true);
                Toast.makeText(TeacherActivity.this,String.valueOf(Global.coursename),Toast.LENGTH_SHORT).show();


            }
        });




        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherActivity.this, AddCourseActivity.class);
                startActivity(intent);

            }
        });
    }



}