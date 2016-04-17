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

public class RegisterVolunteer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_volunteer);

        setTitle("Volunteer Registration");

        Button signup = (Button)findViewById(R.id.signup);

        final EditText uname = (EditText)findViewById(R.id.uname);
        final EditText pass = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText phno = (EditText) findViewById(R.id.phno);
//        final EditText idtype = (EditText) findViewById(R.id.idtype);

        final EditText idno = (EditText) findViewById(R.id.idno);
        final TextView error = (TextView) findViewById(R.id.error);
        //final EditText bloodgroup = (EditText) findViewById(R.id.blood_group);
        final Spinner bg=(Spinner)findViewById(R.id.spinner);
        String[] items=new String[]{"Select Blood Group","O+","O-","A+","A-","B+","B-","AB+","AB-"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        bg.setAdapter(adapter);

        final Spinner id=(Spinner)findViewById(R.id.id);
        String[] items_id=new String[]{"Select ID card Type","Passport","Ration Card","PAN card","Driving License"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items_id);
        id.setAdapter(adapter2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.URL))
                        .build();

                final String uname_text = uname.getText().toString().trim();
                String pass_text = pass.getText().toString().trim();
                final String email_text = email.getText().toString().trim();
                final String name_text = name.getText().toString().trim();
                String phno_text = phno.getText().toString().trim();
                final String idtype_text = id.getSelectedItem().toString().trim();
                String idno_text = idno.getText().toString().trim();
                final String bloodgroup_text = bg.getSelectedItem().toString().trim();
//                Toast.makeText(RegisterVolunteer.this,"Inside onClick" + bloodgroup_text,Toast.LENGTH_LONG).show();
                //String bloodgroup_text = bloodgroup.getText().toString();
                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if (uname_text.trim().equalsIgnoreCase("")) {
                    uname.setError("Please Enter Username");
                    valid = false;
                }
                if (pass_text.equalsIgnoreCase("")) {
                    pass.setError("Please Enter Password");
                    valid = false;
                }
                if (email_text.equalsIgnoreCase("")||!pattern.matcher(email_text).matches()) {
                    email.setError("Please Enter a valid Email");
                    valid = false;
                }
                if (phno_text.equalsIgnoreCase("")) {
                    phno.setError("Please Enter Phone Number");
                    valid = false;
                }
                if (idtype_text.equalsIgnoreCase("Select ID card Type")) {
                    error.setText("Please Select a Valid ID card Type");
                    valid = false;
                }
                if (idno_text.equalsIgnoreCase("")) {
                    idno.setError("Please Enter ID Number");
                    valid = false;
                }
                if (bloodgroup_text.equalsIgnoreCase("Select Blood Group")) {
                    error.setText("Please Select a Valid Blood Group");
                    valid = false;
                }
                if (valid) {
                    RegVol regVol = retrofit.create(RegVol.class);

                    Call<ResponseBody> call = regVol.getData(uname_text, pass_text, name_text, email_text, phno_text, idtype_text, idno_text, bloodgroup_text);//s is blood group selected from the spinner

//                    Toast.makeText(RegisterVolunteer.this, "After call creation", Toast.LENGTH_LONG).show();

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            Toast.makeText(RegisterVolunteer.this, "Outside enque try", Toast.LENGTH_LONG).show();
                            try {

//                                Toast.makeText(RegisterVolunteer.this, "Inside enque try", Toast.LENGTH_LONG).show();
                                String result = response.body().string();
//                                Toast.makeText(RegisterVolunteer.this, result, Toast.LENGTH_LONG).show();
                                int end = result.lastIndexOf("}") + 1;
                                int start = result.indexOf("{");
                                result = result.substring(start, end);
                                JSONObject resultJSONStr = new JSONObject(result);
                                String errorCode = resultJSONStr.getString("error");

                                if (errorCode.equalsIgnoreCase("FALSE")) {
                                    //Confirmation
                                    Intent i = new Intent(RegisterVolunteer.this, RegisterVolunteerConfirm.class);
                                    i.putExtra("username", uname_text);
                                    i.putExtra("email", email_text);
                                    i.putExtra("name", name_text);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(RegisterVolunteer.this,resultJSONStr.getString("error_msg"),Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(RegisterVolunteer.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(RegisterVolunteer.this, t.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{

                    error.setText("Please Fix the Errors and make sure ID Type & Blood Type were selected");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterVolunteer.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
interface RegVol{
    @FormUrlEncoded
    @POST("register_volunteer.php")
    Call<ResponseBody> getData(@Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("email") String email,
                               @Field("phone") String phone, @Field("id_type") String id_type, @Field("id_num") String id_num, @Field("blood_group") String blood_group );
}