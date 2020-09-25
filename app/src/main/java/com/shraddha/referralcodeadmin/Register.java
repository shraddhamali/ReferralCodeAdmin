package com.shraddha.referralcodeadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button regis_btn;
    EditText user_email,user_password,user_name,user_phone;
    String str_name,str_email,str_phone,str_password;
    String url="https://referralcodesystem.000webhostapp.com/reg_vender.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regis_btn=findViewById(R.id.edit_register);

        user_name=findViewById(R.id.edit_name);
        user_email=findViewById(R.id.edit_email);
        user_phone=findViewById(R.id.edit_phone);
        user_password=findViewById(R.id.edit_password);

        regis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterM();

            }
        });

    }

    private void RegisterM() {
        if(user_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }else if(user_email.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }else if(user_phone.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter phone", Toast.LENGTH_SHORT).show();
        }else if(user_password.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else {

            str_name=user_name.getText().toString().trim();
            str_email=user_email.getText().toString().trim();
            str_phone=user_phone.getText().toString().trim();
            str_password=user_password.getText().toString().trim();


            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("name",str_name);
                    params.put("email",str_email);
                    params.put("phone",str_phone);
                    params.put("password",str_password);

                    return params;


                }
            };

            RequestQueue requestQueue=Volley.newRequestQueue(Register.this);
            requestQueue.add(request);
        }
    }
    /*public void RegisterV(View view){
        if(user_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }else if(user_email.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }else if(user_phone.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter phone", Toast.LENGTH_SHORT).show();
        }else if(user_password.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else {

            str_name=user_name.getText().toString().trim();
            str_email=user_email.getText().toString().trim();
            str_phone=user_phone.getText().toString().trim();
            str_password=user_password.getText().toString().trim();


            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("name",str_name);
                    params.put("email",str_email);
                    params.put("phone",str_phone);
                    params.put("password",str_password);

                    return params;


                }
            };

            RequestQueue requestQueue=Volley.newRequestQueue(Register.this);
            requestQueue.add(request);
        }
    }

*/
}