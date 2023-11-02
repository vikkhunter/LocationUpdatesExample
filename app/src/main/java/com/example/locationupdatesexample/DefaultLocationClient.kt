package com.example.locationupdatesexample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class DefaultLocationClient constructor(
    private val context: Context,
    private val client: FusedLocationProviderClient,
) : LocationClient {

    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {
        return callbackFlow<Location> {

            if (context.hasLocationPermission()) {
                throw LocationClient.LocationException("Missiong location permissions")
            }

            val locationManger = context.getSystemService(LOCATION_SERVICE) as LocationManager
            val providers = locationManger.getProviders(true)

            if (providers.isEmpty()) {
                throw LocationClient.LocationException("Missing Provider")
            }


            val locationRequest =
                com.google.android.gms.location.LocationRequest.create().setInterval(10000)
                    .setFastestInterval(10000)

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }
            client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }

    }
}