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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login=(Button) findViewById(R.id.button3);
        Button signup=(Button)findViewById(R.id.button2);
        final EditText uname=(EditText) findViewById(R.id.editText);
        final EditText pass=(EditText)findViewById(R.id.editText2);
        startService(new Intent(this,AndroidLocationServices.class));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://192.168.1.100/bloodapp_api/")
                        .build();
                loginUser lin =retrofit.create(loginUser.class);
                final String uname_text = uname.getText().toString();
                String pass_text = pass.getText().toString();

                //String email="abinbhattacharya@gmail.com";
                Call<ResponseBody> call = lin.getData(uname_text,pass_text);
                //Toast.makeText(MainActivity.this, "hello i am here", Toast.LENGTH_SHORT).show();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try{
                            String result = response.body().string();
                            int end = result.lastIndexOf("}")+1;
                            int start = result.indexOf("{");
                            result = result.substring(start,end);
                            JSONObject resultJSONStr = new JSONObject(result);
                            String errorCode = resultJSONStr.getString("error");
                            //Toast.makeText(MainActivity.this, "hello i am here", Toast.LENGTH_SHORT).show();
                            if(errorCode.equals("false"))
                            {
                                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                                i.putExtra("name",uname_text);
                                startActivity(i);
                            }
                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main22Activity.class);
                startActivity(i);
            }
        });

    }

}
interface loginUser{
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> getData(@Field("username") String uname_text,@Field("password") String pass_text);
}