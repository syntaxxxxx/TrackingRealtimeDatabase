package com.syntax.tutorialtrackingmvp.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class TrackingService : Service() {

    private var mAuth: FirebaseAuth? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mAuth = FirebaseAuth.getInstance()
        requestLocationUpdates()
    }

    var stopReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            // Unregister the BroadcastReceiver when the notification is tapped
            unregisterReceiver(this)

            //Stop the Service//
            stopSelf()
        }
    }

    private fun requestLocationUpdates() {
        val request = LocationRequest()

        // Specify how often your app should request the device’s location
        request.setInterval(1000) // 1 seconds

        // Get the most accurate location data available
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        val client = LocationServices.getFusedLocationProviderClient(this)
        val path = "locations"
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        // If the app currently has access to the location permission
        if (permission == PackageManager.PERMISSION_GRANTED) {

            // then request location updates//
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {

                    // Get a reference to the database, so your app can perform read and write operations
                    val dr = FirebaseDatabase.getInstance().getReference(path)
                    val location = locationResult.getLastLocation()
                    if (location != null) {
                        // Save the location data to the database//
                        dr.child(mAuth?.currentUser?.uid!!).setValue(location)
                    }
                }
            }, null)
        }
    }
}