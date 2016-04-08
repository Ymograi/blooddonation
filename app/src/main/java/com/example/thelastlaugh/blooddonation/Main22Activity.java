package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

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
        Button signup=(Button) findViewById(R.id.button2);
        final EditText uname=(EditText) findViewById(R.id.editText);
        final EditText pass=(EditText)findViewById(R.id.password);
        final EditText email=(EditText)findViewById(R.id.editText3);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.100/bloodapp_api/")
                        .build();
                registerUser rusr = retrofit.create(registerUser.class);
                final String uname_text = uname.getText().toString();
                final String pass_text = pass.getText().toString();
                final String email_text = email.getText().toString();
                //String email="abinbhattacharya@gmail.com";
                Call<ResponseBody> call = rusr.getData(uname_text,pass_text,email_text);
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
                                Intent i = new Intent(Main22Activity.this, Main23Activity.class);
                                i.putExtra("name", uname_text);
                                i.putExtra("email", email_text);
                                i.putExtra("password",pass_text);
                                startActivity(i);
                            }
                            Toast.makeText(Main22Activity.this, result, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(Main22Activity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });

    }

}
interface registerUser{
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> getData(@Field("username") String uname_text,@Field("password") String pass_text,@Field("email") String email_text);
}