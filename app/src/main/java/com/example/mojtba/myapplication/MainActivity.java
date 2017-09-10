package com.example.mojtba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import static android.widget.RadioGroup.*;


public class MainActivity extends AppCompatActivity {


    EditText name, id, email, password;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Button submit;
    RadioGroup rg;
    RadioButton trb,srb;
    RequestQueue requestQueue;
    String insertStudentUrl = "http://10.42.0.1/insertStudent.php";
    String insertTeacherUrl = "http://10.42.0.1/insertTeacher.php";
    //String insertStudentUrl = "http://10.0.2.2/insertStudent.php";
    //String insertTeacherUrl = "http://10.0.2.2/insertTeacher.php";


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.radioGroup);

        srb = (RadioButton) findViewById(R.id.sradio);
        trb=(RadioButton) findViewById(R.id.tradio);

        spinner=(Spinner) findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.Level,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
      //      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            //}
        //});

        submit=(Button)findViewById(R.id.insert);

        id=(EditText) findViewById(R.id.etid) ;
        name=(EditText)findViewById(R.id.etname);
        email=(EditText)findViewById(R.id.etemail);
        password=(EditText)findViewById(R.id.etpassword);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        rg.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==trb.getId())
                {
                    spinner.setVisibility(View.GONE);

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                StringRequest request = new StringRequest(Request.Method.POST, insertTeacherUrl, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        System.out.println(response.toString());
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.names().get(0).equals("reg_failed")) {
                                                Toast.makeText(getApplicationContext(), jsonObject.getString("reg_failed"), Toast.LENGTH_LONG).show();

                                            } else if (jsonObject.names().get(0).equals("empty")) {
                                                Toast.makeText(getApplicationContext(), jsonObject.getString("empty"), Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }) {

                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> parameters = new HashMap<String, String>();
                                        parameters.put("tid", id.getText().toString());
                                        parameters.put("name", name.getText().toString());
                                        parameters.put("password", password.getText().toString());
                                        parameters.put("email", email.getText().toString());

                                        return parameters;
                                    }
                                };
                                requestQueue.add(request);


                        }

                    });
                }


                //Teacher













                //STUDENT




                else {
                    spinner.setVisibility(View.VISIBLE);

                        submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (spinner.getSelectedItem().toString().equals("Level")){
                                Toast.makeText(getApplicationContext(),"Select Level",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                StringRequest request = new StringRequest(Request.Method.POST, insertStudentUrl, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        System.out.println(response.toString());
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.names().get(0).equals("reg_failed")) {
                                                Toast.makeText(getApplicationContext(), jsonObject.getString("reg_failed"), Toast.LENGTH_LONG).show();

                                            } else if (jsonObject.names().get(0).equals("empty")) {
                                                Toast.makeText(getApplicationContext(), jsonObject.getString("empty"), Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }) {

                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {

                                        Map<String, String> parameters = new HashMap<String, String>();
                                        parameters.put("sid", id.getText().toString());
                                        parameters.put("name", name.getText().toString());
                                        parameters.put("password", password.getText().toString());
                                        parameters.put("batch", spinner.getSelectedItem().toString());
                                        parameters.put("email", email.getText().toString());

                                        return parameters;

                                    }
                                };

                                requestQueue.add(request);
                            }
                        }

                    });


            }
                }});

        submit.setOnClickListener(new View.OnClickListener(){
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