package com.shraddha.referralcodeadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Login extends AppCompatActivity {

    Button login_btn;
    EditText user_email,user_password;
    TextView register;
    String str_email,str_password;
    String url="https://referralcodesystem.000webhostapp.com/login_vender.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn=findViewById(R.id.loginBtn);

        user_email=findViewById(R.id.edit_email);
        user_password=findViewById(R.id.edit_password);
        //user_otp=findViewById(R.id.edit_otp);

        register=findViewById(R.id.user_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginM();
            }
        });

    }

    private void LoginM() {
      if(user_email.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }else if(user_password.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else{

          final ProgressDialog progressDialog = new ProgressDialog(this);
          progressDialog.setMessage("Please Wait..");


          progressDialog.show();
          str_email=user_email.getText().toString().trim();
          str_password=user_password.getText().toString().trim();


          StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                  progressDialog.dismiss();

                 /* startActivity(new Intent(getApplicationContext(),Profile.class));
                  Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();*/
                  if(response.equals(1)){
                      user_email.setText("");
                      user_password.setText("");
                      startActivity(new Intent(getApplicationContext(),Profile.class));
//                      Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();


                  }
                    else {
                      Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                  }


              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

              }
          }
          ){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String,String> params = new HashMap<String, String>();


                  params.put("email",str_email);
                  params.put("password",str_password);

                  return params;


              }
          };

          RequestQueue requestQueue=Volley.newRequestQueue(Login.this);
          requestQueue.add(request);

      }
    }

}
