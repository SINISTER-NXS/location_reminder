package com.example.locationreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.*;
import com.google.android.gms.location.*;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.GeofencingApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GeofencingClient geofencingClient;

        private GeofencingRequest mGeofencingRequest;
        private PendingIntent mGeofencePendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        private GoogleApiClient mGoogleApiClient;

        List<Geofence> geofences = new ArrayList<>();
        geofences.add(new Geofence.Builder()
                .setRequestId("my_geofence")
                .setCircularRegion(37.4220, -122.0841, 100) // Latitude, Longitude, Radius (in meters)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());

        mGeofencingRequest = new GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofences(geofences)
                .build();
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        intent.setAction("com.example.geofencing.ACTION_RECEIVE_GEOFENCE_EVENT");
        mGeofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
