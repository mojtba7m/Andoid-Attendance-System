package com.example.mojtba.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mojtba on 08/09/17.
 */

public class TeacherList extends AppCompatActivity  {
    WifiApManager wifiApManager;
    Button End;
    FinishScanListener f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_list);
        f = new FinishScanListener() {
            @Override
            public void onFinishScan(ArrayList<ClientScanResult> clients) {
                try{
                    Toast.makeText(TeacherList.this, String.valueOf(clients.size()), Toast.LENGTH_SHORT).show();

                }catch (Exception c){
                    Toast.makeText(TeacherList.this, c.getMessage(), Toast.LENGTH_SHORT).show();
                }
                RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.res);

                my_recycler_view.setHasFixedSize(true);


                SectionListDataAdapte ad = new SectionListDataAdapte(getApplicationContext(), clients);
                my_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                my_recycler_view.setNestedScrollingEnabled(false);
                my_recycler_view.setAdapter(ad);
            }


        };
        wifiApManager = new WifiApManager(getApplicationContext(), null);


        End = (Button) findViewById(R.id.end);
        End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wifiApManager.getClientList(true, f);

            }
        });
    }
}

