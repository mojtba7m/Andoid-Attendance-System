package com.example.mojtba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mojtba on 09/06/17.
 */

public class AddCourseActivity extends AppCompatActivity{
    Button submit;
    EditText name,cid,tid;
    RequestQueue requestQueue;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private static final String INSERT_URL="http://10.42.0.1/insertCourse.php";
//    private static final String INSERT_URL="http://10.0.2.2/insertCourse.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_activity);
        submit=(Button)findViewById(R.id.ok);
        name=(EditText)findViewById(R.id.etCourseName);
        cid=(EditText)findViewById(R.id.etCourseID);
        tid=(EditText)findViewById(R.id.etTID);
        spinner=(Spinner)findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.Level,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                StringRequest request = new StringRequest(Request.Method.POST, INSERT_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (spinner.getSelectedItem().toString().equals("Level")){
                            Toast.makeText(getApplicationContext(),"Select Level",Toast.LENGTH_SHORT).show();
                        }
                        else {


                            System.out.println(response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                                    name.setText("");
                                    cid.setText("");
                                    tid.setText("");
                                    spinner.setSelection(0);
                                } else if (jsonObject.names().get(0).equals("reg_failed")) {
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("reg_failed"), Toast.LENGTH_SHORT).show();
                                } else if (jsonObject.names().get(0).equals("empty")) {
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("empty"), Toast.LENGTH_SHORT).show();

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){

                            }
                })

                    {

                        @Override
                            protected Map<String, String> getParams () throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("name", name.getText().toString());
                            parameters.put("cid", cid.getText().toString());
                            parameters.put("tid", tid.getText().toString());
                            parameters.put("batch", spinner.getSelectedItem().toString());


                            return parameters;
                        }

                    };
                requestQueue.add(request);

            }
        });
    }
}

