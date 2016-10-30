package com.example.good_candy;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity implements LocationListener {

    static final long MIN_TIME = 1;
    static final float MIN_DISTANCE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    11 );
        }

        final Button button = (Button) findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
        }
        double longitude = 0.0;
        double latitude = 0.0;
        final LocationListener thi=this;
        final Context test=this;
        final Activity test2 = this;
        /*if(location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }*/

        System.out.println("Lat = " + latitude + "Long = " + longitude);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( ContextCompat.checkSelfPermission( test, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                    ActivityCompat.requestPermissions( test2, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                            11 );
                }
                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, thi);
                double longitude = 0.0;
                double latitude = 0.0;
                if(location != null) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                }


                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);
                try{
                    String address = "http://linuxbro.com/coord?lat="+Double.toString(latitude)+"&long="+Double.toString(longitude);
                    URL myURL = new URL(address);
                    HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
                    myURLConnection.connect();
                    myURLConnection.getResponseMessage();
                }
                catch(MalformedURLException e){

                }
                catch (IOException e){

                }

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
