package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class Verify_a_donor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_a_donor);

        setTitle("Verify a Volunteer");

        final String name=getIntent().getStringExtra("name");
        final String username = getIntent().getStringExtra("username");
        final String blood=getIntent().getStringExtra("blood");
        final String phno=getIntent().getStringExtra("phno");
        final String admin = getIntent().getStringExtra("admin");
        TextView nam=(TextView)findViewById(R.id.name);
        nam.setText("Name: "+name);
        TextView blo=(TextView)findViewById(R.id.bg);
        blo.setText("Blood Group: "+blood);
        TextView phn=(TextView)findViewById(R.id.ph);
        phn.setText("Phone: "+phno);
        TextView usernameTV = (TextView)findViewById(R.id.username);
        usernameTV.setText("Username: " + username);
        Button call=(Button)findViewById(R.id.cal);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phno));
                startActivity(intent);
            }
        });
        final RadioGroup validate = (RadioGroup)findViewById(R.id.validate);


        final Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] message = new String[1];
                RadioButton selected = (RadioButton) findViewById(validate.getCheckedRadioButtonId());
                String validate_text = selected.getText().toString();
                message[0] = username + " verified successfully.";
//                Toast.makeText(Verify_a_donor.this,validate_text,Toast.LENGTH_LONG).show();
                if(validate_text.equalsIgnoreCase("Valid")){
                    selected.setEnabled(false);
                    RadioButton invalid = (RadioButton) findViewById(R.id.invalid);
                    invalid.setEnabled(false);
                    submit.setEnabled(false);
//                    ((ViewManager)validate.getParent()).removeView(validate);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getResources().getString(R.string.URL))
                            .build();
                    verifyADonor verdonor = retrofit.create(verifyADonor.class);
                    Call<ResponseBody> call =verdonor.getData(username,"admin",admin);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try{
                                String result = response.body().string();
//                                Toast.makeText(Verify_a_donor.this, result, Toast.LENGTH_SHORT).show();
                                int end = result.lastIndexOf("}") + 1;
                                int start = result.indexOf("{");
                                result = result.substring(start, end);
                                JSONObject resultJSONStr = new JSONObject(result);
                                String error = resultJSONStr.getString("error");
//                                Toast.makeText(Verify_a_donor.this, "Error is " + error, Toast.LENGTH_SHORT).show();
                                if (error.equalsIgnoreCase("false")){
                                    message[0] = username + " verified successfully.";
//                                    Toast.makeText(Verify_a_donor.this, "Message is " + message[0], Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Verify_a_donor.this,resultJSONStr.getString("error_message"),Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (Exception e){
                                Toast.makeText(Verify_a_donor.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Verify_a_donor.this, t.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else
                {
                    message[0] = username + " was left unverified.";
                }
//                Toast.makeText(Verify_a_donor.this, "Message outside if is " + message[0], Toast.LENGTH_SHORT).show();
                TextView confirmation = (TextView) findViewById(R.id.confirmation);
                confirmation.setText(message[0]);
            }
        });

//        WebView webview=(WebView)findViewById(R.id.webView);
//        webview.loadUrl("https://www.gmail.com");

    }

    @Override
    public void onBackPressed() {
        final String admin = getIntent().getStringExtra("admin");
        Intent i = new Intent(Verify_a_donor.this,verify_list.class);
        i.putExtra("user",admin);
        i.putExtra("type","admin");
        startActivity(i);
        super.onBackPressed();
    }
}
interface verifyADonor{
    @FormUrlEncoded
    @POST("verify_a_donor.php")
    Call<ResponseBody> getData(@Field("username") String username, @Field("type") String type, @Field("valid") String valid);
}
