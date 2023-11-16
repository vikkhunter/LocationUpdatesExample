package com.example.locationupdatesexample.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.locationupdatesexample.R
import java.util.Calendar

class AlarmManagerExample : AppCompatActivity() {

    //communication between android components Activity, Service, Broadcast Receivers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_manager_example)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        findViewById<Button>(R.id.button_one).setOnClickListener {
            val intent = Intent(this, AlarmReceiver::class.java)
            intent.action = "set_alarm"
            intent.putExtra("message", "Wake up")
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_MUTABLE
            )
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.SECOND, 10)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            val intent = Intent(this, AlarmReceiver::class.java)
            intent.action = "set_alarm"
            intent.putExtra("message", "Wake up")
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_MUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }
}