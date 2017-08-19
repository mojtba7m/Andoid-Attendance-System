package com.example.mojtba.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class HomeActivity extends AppCompatActivity {
Button login,signup;
    EditText id , password;
    RadioButton srb,trb;
    RadioGroup rg;

    private RequestQueue requestQueue;
    private static final String SLOGIN_URL="http://10.42.0.1/studentLogin.php";
    private static final String TLOGIN_URL="http://10.42.0.1/teacherLogin.php";

    private StringRequest request;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        rg = (RadioGroup) findViewById(R.id.RadioGroup);
        srb = (RadioButton) findViewById(R.id.radioS);
        trb = (RadioButton) findViewById(R.id.radioT);
        id = (EditText) findViewById(R.id.etid);
        password=(EditText)findViewById(R.id.etpassword);
        requestQueue = Volley.newRequestQueue(this);


        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (checkedId == trb.getId()) {

                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(id.getText().toString().equals("")||password.getText().toString().equals(""))
                            {
                                Toast.makeText(getApplicationContext(),"you must type both inputs",Toast.LENGTH_SHORT).show();
                            }
                            request=new StringRequest(Request.Method.POST, TLOGIN_URL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject=new JSONObject(response);
                                        if (jsonObject.names().get(0).equals("success")){

                                            Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(HomeActivity.this, TeacherActivity.class);
                                            startActivity(intent);
                                            finish();
                                            //id.setText("");
                                            //password.setText("");
                                        }
                                        else if (jsonObject.names().get(0).equals("error")) {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("NotActive"),Toast.LENGTH_SHORT).show();

                                        }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> hashMap=new HashMap<String, String>();
                                    hashMap.put("tid",id.getText().toString());
                                    hashMap.put("password",password.getText().toString());
                                    return hashMap;
                                }
                            };
                            requestQueue.add(request);

                        }
                    });
                }
                //TEACHER








                //STUDENT
                else {

                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(id.getText().toString().equals("")||password.getText().toString().equals(""))
                            {
                                Toast.makeText(getApplicationContext(),"you must type both inputs",Toast.LENGTH_SHORT).show();
                            }
                            request=new StringRequest(Request.Method.POST, SLOGIN_URL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject=new JSONObject(response);
                                        if (jsonObject.names().get(0).equals("success")){

                                            Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(HomeActivity.this, StudentActivity.class);
                                            startActivity(intent);
                                            finish();
                                            //id.setText("");
                                            //password.setText("");
                                        }
                                        else if (jsonObject.names().get(0).equals("error")) {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("NotActive"),Toast.LENGTH_SHORT).show();

                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> hashMap=new HashMap<String, String>();
                                    hashMap.put("sid",id.getText().toString());
                                    hashMap.put("password",password.getText().toString());
                                    return hashMap;
                                }
                            };
                            requestQueue.add(request);

                        }
                    });

                }

            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (srb.isChecked()||!trb.isChecked()){
                    Toast.makeText(getApplicationContext(),"please choose student or teacher",Toast.LENGTH_SHORT).show();
                }
            }
        }
        );



    }


    }
