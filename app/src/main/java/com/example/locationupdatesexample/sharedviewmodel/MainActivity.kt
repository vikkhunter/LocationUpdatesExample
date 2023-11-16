package com.example.locationupdatesexample.sharedviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.locationupdatesexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout1, firstFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout2, secondFragment).commit()

    }
}