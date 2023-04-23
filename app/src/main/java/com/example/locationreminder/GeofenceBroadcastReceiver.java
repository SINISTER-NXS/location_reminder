package com.example.locationreminder;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

public class GeofenceBroadcastReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent GeofencingEvent = null;
            // Get the geofencing event
            GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

            // Check if the event has error
            if (geofencingEvent.hasError()) {
                Log.e(TAG, "GeofencingEvent error: " + geofencingEvent.getErrorCode());
                return;
            }

            // Get the transition type
            int transitionType = geofencingEvent.getGeofenceTransition();

            // Check if the device has entered or exited the geofence
            if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Toast.makeText(context, "You have entered your location", Toast.LENGTH_SHORT).show();
            } else if (transitionType == Geofence.GEOFENCE_TRANSITION_EXIT) {
                Toast.makeText(context, "You have exited your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

