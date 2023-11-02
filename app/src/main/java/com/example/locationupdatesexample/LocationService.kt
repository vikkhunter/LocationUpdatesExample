package com.example.locationupdatesexample

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.locationupdatesexample.Constants.LOCATION_UPDATES_INTERVAL
import com.example.locationupdatesexample.Constants.NOTIFICATION_CHANNEL_ID
import com.example.locationupdatesexample.Constants.START
import com.example.locationupdatesexample.Constants.STOP
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.supervisorScope

class LocationService : Service() {

    private lateinit var client: FusedLocationProviderClient
    private lateinit var locationClient: LocationClient
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        client = LocationServices.getFusedLocationProviderClient(this)
        locationClient = DefaultLocationClient(this, client)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START -> start()
            STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun stop() {
        stopSelf()
    }

    private val TAG = "LocationService"

    @SuppressLint("NotificationPermission")
    private fun start() {

        Log.d(TAG, "start: service started")

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setContentTitle("Tracking Location...")
            .setContentText("Location : null")
            .setSmallIcon(R.drawable.ic_notification)
            .setOngoing(true)

        val notificationManger = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(LOCATION_UPDATES_INTERVAL)
            .catch {
                Log.d(TAG, "Exception: ${it.message}")
            }
            .onEach { location ->
                Log.d(TAG, "location updates: ${location.latitude} ${location.longitude}")
                val lat = location.latitude
                val long = location.longitude
                val updatedNotification =
                    notification.setContentText("Location: $lat,$long").build()
                notificationManger.notify(1, updatedNotification)
            }.launchIn(serviceScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Log.d(TAG, "Service Stopped")
    }

}