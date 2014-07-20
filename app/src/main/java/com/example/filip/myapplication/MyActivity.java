package com.example.filip.myapplication;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity {

    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        gps = new GPSTracker(this);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gps.canGetLocation()) {
                    Location lastLocation = gps.getLocation();
                    ((TextView) findViewById(R.id.textViewLat)).setText(String.valueOf(lastLocation.getLatitude()));
                    ((TextView) findViewById(R.id.textViewLng)).setText(String.valueOf(lastLocation.getLongitude()));
                    ((TextView) findViewById(R.id.textViewAcc)).setText(String.valueOf(lastLocation.getAccuracy()));
                    ((TextView) findViewById(R.id.textView17)).setText(String.valueOf(lastLocation.getAltitude()));
                    ((TextView) findViewById(R.id.textView18)).setText(String.valueOf(lastLocation.getBearing()));
                    ((TextView) findViewById(R.id.textViewTime)).setText(String.valueOf(lastLocation.getTime()));
                    ((TextView) findViewById(R.id.textViewProvider)).setText(String.valueOf(lastLocation.getProvider()));
                    ((TextView) findViewById(R.id.textViewSpeed)).setText(String.valueOf(lastLocation.getSpeed()));
//                    ((TextView) findViewById(R.id.textViewSat)).setText(String.valueOf(lastLocation.getExtras().getInt("satellites")));
                } else {
                    gps.showSettingsAlert();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        gps.stopUsingGPS();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
