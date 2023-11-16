package com.example.locationupdatesexample.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "onReceive: ")
        Toast.makeText(
            context,
            "${intent.getStringExtra("message")}",
            Toast.LENGTH_SHORT
        ).show()

    }
}
