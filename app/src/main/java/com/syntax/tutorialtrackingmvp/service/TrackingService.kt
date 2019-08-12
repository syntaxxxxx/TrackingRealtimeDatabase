package com.syntax.tutorialtrackingmvp.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder


@SuppressLint("Registered")
class TrackingService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }
}