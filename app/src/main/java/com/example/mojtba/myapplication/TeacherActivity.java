package com.example.mojtba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        start=(Button)findViewById(R.id.Search);
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