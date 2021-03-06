package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Main22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        setTitle("Seeker Registration");

        Button signup=(Button) findViewById(R.id.signup);

        //main.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

        final EditText uname=(EditText)findViewById(R.id.uname);
        final EditText pass=(EditText)findViewById(R.id.password);
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText phno=(EditText)findViewById(R.id.phno);
//        final EditText idtype=(EditText)findViewById(R.id.idtype);
        final EditText idno=(EditText)findViewById(R.id.idno);
        final TextView error=(TextView)findViewById(R.id.textView8);
        //passport, ration card, PAN card, or driving license
        final Spinner id=(Spinner)findViewById(R.id.id);
        String[] items=new String[]{"Select ID card Type","Passport","Ration Card","PAN card","Driving License"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        id.setAdapter(adapter);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean valid=true;
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.URL))
                        .build();
                registerUser rusr = retrofit.create(registerUser.class);
                final String uname_text = uname.getText().toString().trim();
                final String pass_text = pass.getText().toString().trim();
                final String email_text = email.getText().toString().trim();
                final String name_text = name.getText().toString().trim();
                final String phno_text = phno.getText().toString().trim();
                final String idtype_text = id.getSelectedItem().toString().trim();
                final String idno_text = idno.getText().toString().trim();

                Pattern pattern = Patterns.EMAIL_ADDRESS;

                if(uname_text.equalsIgnoreCase("")) {
                    uname.setError("Please Enter Username");
                    valid=false;
                }
                if(pass_text.equalsIgnoreCase("")) {
                    pass.setError("Please Enter Password");
                    valid=false;
                }
                if(email_text.equalsIgnoreCase("")||!pattern.matcher(email_text).matches()) {
                    email.setError("Please Enter a valid Email");
                    valid=false;
                }
                if(phno_text.equalsIgnoreCase("")) {
                    phno.setError("Please Enter Phone Number");
                    valid=false;
                }
                if(idtype_text.equalsIgnoreCase("Select ID card Type")) {
                    error.setText("Please Select a Valid ID card Type");
                    valid=false;
                }
                if(idno_text.equalsIgnoreCase("")) {
                    idno.setError("Please Enter ID Number");
                    valid=false;
                }
                //String email="abinbhattacharya@gmail.com";
                if(valid) {
                    Call<ResponseBody> call = rusr.getData(uname_text, pass_text, email_text, name_text, phno_text, idtype_text, idno_text);
                    //Toast.makeText(MainActivity.this, "hello i am here", Toast.LENGTH_SHORT).show();
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String result = response.body().string();
                                int end = result.lastIndexOf("}") + 1;
                                int start = result.indexOf("{");
                                result = result.substring(start, end);
                                JSONObject resultJSONStr = new JSONObject(result);
                                String errorCode = resultJSONStr.getString("error");
                                //Toast.makeText(MainActivity.this, "hello i am here", Toast.LENGTH_SHORT).show();
                                if (errorCode.equals("false")) {
                                    error.setText("Thank you for Signing up "+ name_text);
                                    Intent i = new Intent(Main22Activity.this, Main23Activity.class);
                                    i.putExtra("username", uname_text);
                                    i.putExtra("name",name_text);
                                    i.putExtra("email", email_text);
                                    i.putExtra("password", pass_text);
                                    startActivity(i);
                                }
                                else{
                                    String error_msg = resultJSONStr.getString("error_msg");
//                                    Toast.makeText(Main22Activity.this, result, Toast.LENGTH_LONG).show();
                                    Toast.makeText(Main22Activity.this,error_msg,Toast.LENGTH_LONG).show();
                                }
//                                Toast.makeText(Main22Activity.this, result, Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(Main22Activity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
                else{

                    error.setText("Please Fix the Errors and make sure ID Type was selected");
                }


            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Main22Activity.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }

}
interface registerUser{
    @FormUrlEncoded
    @POST("register_seeker.php")
    Call<ResponseBody> getData(@Field("username") String uname_text,@Field("password") String pass_text,@Field("email") String email_text,@Field("name")
    String name_text,@Field("phone") String phno_text,@Field("id_type") String idtype_text,@Field("id_num") String idno_text);
}