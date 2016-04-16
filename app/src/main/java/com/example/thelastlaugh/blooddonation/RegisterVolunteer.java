package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class RegisterVolunteer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_volunteer);

        Button signup = (Button)findViewById(R.id.signup);

        final EditText uname = (EditText)findViewById(R.id.uname);
        final EditText pass = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText phno = (EditText) findViewById(R.id.phno);
        final EditText idtype = (EditText) findViewById(R.id.idtype);
        final EditText idno = (EditText) findViewById(R.id.idno);
        //final EditText bloodgroup = (EditText) findViewById(R.id.blood_group);
        final Spinner bg=(Spinner)findViewById(R.id.spinner);
        String[] items=new String[]{"Select Blood Group","O+ve","O-ve","A+","A-","B+","B-","AB+","AB-"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        bg.setAdapter(adapter);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s=bg.getSelectedItem().toString();
                Toast.makeText(RegisterVolunteer.this,"Inside onClick"+s,Toast.LENGTH_LONG).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.URL))
                        .build();

                final String uname_text = uname.getText().toString();
                String pass_text = pass.getText().toString();
                final String email_text = email.getText().toString();
                final String name_text = name.getText().toString();
                String phno_text = phno.getText().toString();
                String idtype_text = idtype.getText().toString();
                String idno_text = idno.getText().toString();
                //String bloodgroup_text = bloodgroup.getText().toString();

                RegVol regVol = retrofit.create(RegVol.class);

                Call<ResponseBody> call = regVol.getData(uname_text,pass_text,name_text,email_text,phno_text,idtype_text,idno_text,s);//s is blood group selected from the spinner

                Toast.makeText(RegisterVolunteer.this,"After call creation",Toast.LENGTH_LONG).show();

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RegisterVolunteer.this,"Outside enque try",Toast.LENGTH_LONG).show();
                        try{

                            Toast.makeText(RegisterVolunteer.this,"Inside enque try",Toast.LENGTH_LONG).show();
                            String result = response.body().string();
                            Toast.makeText(RegisterVolunteer.this,result,Toast.LENGTH_LONG).show();
                            int end = result.lastIndexOf("}") + 1;
                            int start = result.indexOf("{");
                            result = result.substring(start,end);
                            JSONObject resultJSONStr = new JSONObject(result);
                            String errorCode = resultJSONStr.getString("error");

                            if(errorCode.equalsIgnoreCase("FALSE")){
                                //Confirmation
                                Intent i = new Intent(RegisterVolunteer.this,RegisterVolunteerConfirm.class);
                                i.putExtra("username",uname_text);
                                i.putExtra("email",email_text);
                                i.putExtra("name",name_text);
                                startActivity(i);
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(RegisterVolunteer.this,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegisterVolunteer.this,t.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
}
interface RegVol{
    @FormUrlEncoded
    @POST("register_volunteer.php")
    Call<ResponseBody> getData(@Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("email") String email,
                               @Field("phone") String phone, @Field("id_type") String id_type, @Field("id_num") String id_num, @Field("blood_group") String blood_group );
}