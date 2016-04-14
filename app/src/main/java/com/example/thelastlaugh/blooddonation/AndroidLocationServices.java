package com.example.thelastlaugh.blooddonation;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class AndroidLocationServices extends Service {
    PowerManager.WakeLock wakeLock;
    private static boolean flag = true;

    private String uname;


    private LocationManager locationManager;

    public AndroidLocationServices() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);
//        if (intent!=null) {
//            uname = intent.getStringExtra("username");
//            flag = false;
//        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        uname = sharedPreferences.getString("username","");


        Toast.makeText(AndroidLocationServices.this,"Username in service is " + uname,Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        PowerManager pm = (PowerManager) getSystemService(this.POWER_SERVICE);

        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

        // Toast.makeText(getApplicationContext(), "Service Created",
        // Toast.LENGTH_SHORT).show();

        Log.e("Google", "Service Created");

    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);

//        if(flag){
//            uname = intent.getStringExtra("username");
//            flag = false;
//        }

//        uname = intent.getStringExtra("username");
//        Toast.makeText(AndroidLocationServices.this,"Username in service onStart is " + uname,Toast.LENGTH_LONG).show();
        Log.e("Google", "Service Started");

        locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1 * 1000, 500, listener);

    }

    private LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub

            Log.e("Google", "Location Changed");

            if (location == null)
                return;

            if (isConnectingToInternet(getApplicationContext())) {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();

                try {
                    Log.e("latitude", location.getLatitude() + "");
                    Log.e("longitude", location.getLongitude() + "");

                    String lat = Double.toString(location.getLatitude());
                    String longi = Double.toString(location.getLongitude());
                    //String uname = "ymograi";
                    Toast.makeText(AndroidLocationServices.this,"Username in try is " + uname,Toast.LENGTH_LONG);
                    Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(getResources().getString(R.string.URL))
                            .build();
                    reportLocation loc =retrofit.create(reportLocation.class);

                    Call<ResponseBody> call = loc.getData(uname,lat,longi);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try{Toast.makeText(AndroidLocationServices.this,"inlocationtry",Toast.LENGTH_LONG).show();
                                String result = response.body().string();
//                                int end = result.lastIndexOf("}")+1;
//                                int start = result.indexOf("{");
//                                result = result.substring(start,end);
//                                JSONObject resultJSONStr = new JSONObject(result);
//                                String errorCode = resultJSONStr.getString("error");
                                Toast.makeText(AndroidLocationServices.this, result, Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e){
                                Toast.makeText(AndroidLocationServices.this,e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(AndroidLocationServices.this, t.toString(), Toast.LENGTH_LONG).show();

                        }
                    });

                    //Log.e("request", jsonArray.toString());

//                    new LocationWebService().execute(new String[] {
//                            Constants.TRACK_URL, jsonArray.toString() });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.e("Google","Service destroyed");
        Toast.makeText(AndroidLocationServices.this,"Service destroyed",Toast.LENGTH_LONG).show();
        wakeLock.release();

    }

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

}
interface reportLocation{
    @FormUrlEncoded
    @POST("loc.php")
    Call<ResponseBody> getData(@Field("username") String uname_text,@Field("lat") String lat, @Field("long") String longi);
}