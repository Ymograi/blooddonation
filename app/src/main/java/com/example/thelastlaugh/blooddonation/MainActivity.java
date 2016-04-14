package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        Button login=(Button) findViewById(R.id.Signin);
        Button vsignup=(Button)findViewById(R.id.vsignup);
        Button ssignup  = (Button)findViewById(R.id.ssignup);
        final EditText uname=(EditText) findViewById(R.id.username);
        final EditText pass=(EditText)findViewById(R.id.password);
        final RadioGroup usertype=(RadioGroup)findViewById(R.id.radioGroup);
//        Intent i=new Intent(MainActivity.this,AndroidLocationServices.class);
//        startService(i);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid=true;
                if(uname.getText().toString().trim().equals("")){
                    uname.setError("Please enter username.");
                    valid=false;
                }

                if(pass.getText().toString().equals("")){
                    pass.setError("Please enter password.");
                    valid=false;
                }

                int selectedUsertype = usertype.getCheckedRadioButtonId();

                RadioButton checkedusertype = (RadioButton)findViewById(selectedUsertype);
//                Toast.makeText(MainActivity.this,"Before valid " + uname,Toast.LENGTH_LONG).show();
                if(valid) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getResources().getString(R.string.URL))
                            .build();
                    loginUser lin = retrofit.create(loginUser.class);
                    final String uname_text = uname.getText().toString();
                    String pass_text = pass.getText().toString();
                    final String type_text = checkedusertype.getText().toString().toLowerCase();
                    Toast.makeText(MainActivity.this,type_text,Toast.LENGTH_LONG).show();
//                    Toast.makeText(MainActivity.this,"Inside valid " + uname_text,Toast.LENGTH_LONG).show();
                    //String email="abinbhattacharya@gmail.com";
                    Call<ResponseBody> call = lin.getData(uname_text, pass_text,type_text);
//                    Toast.makeText(MainActivity.this, "call created", Toast.LENGTH_SHORT).show();
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String result = response.body().string();
                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                                int end = result.lastIndexOf("}") + 1;
                                int start = result.indexOf("{");
                                result = result.substring(start, end);
                                JSONObject resultJSONStr = new JSONObject(result);
                                String errorCode = resultJSONStr.getString("error");
                               Toast.makeText(MainActivity.this, "hello i am here", Toast.LENGTH_SHORT).show();
                                if (errorCode.equals("false")) {
                                    String userType = resultJSONStr.getString("type");

                                    Intent i;

                                    if(userType.equalsIgnoreCase("admin"))
                                        i = new Intent(MainActivity.this, AdminHomepage.class);

                                    else if(userType.equalsIgnoreCase("volunteer"))
                                        i = new Intent(MainActivity.this, VolunteerHomepage.class);

                                    else
                                        i = new Intent(MainActivity.this, SeekerHomepage.class);

//                                    Toast.makeText(MainActivity.this,"Inside onResponse " + uname_text,Toast.LENGTH_LONG).show();

                                    i.putExtra("uname_text", uname_text);
                                    i.putExtra("type", userType);
                                    startActivity(i);
                                }
                                Toast.makeText(MainActivity.this, result+"afterstartactivity", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }
            }
        });
        vsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterVolunteer.class);
                startActivity(i);
            }
        });
        ssignup.setOnClickListener(new View.OnClickListener() {
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
    Call<ResponseBody> getData(@Field("username") String uname_text,@Field("password") String pass_text, @Field("type") String type_text);
}