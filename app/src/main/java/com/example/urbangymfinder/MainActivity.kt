package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //funcio login
        fun events() {
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        // funcio on click button
        findViewById<Button>(R.id.button2).setOnClickListener {
            events()
        }
    }
}
