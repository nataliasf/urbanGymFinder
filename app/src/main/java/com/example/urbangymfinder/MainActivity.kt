package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //funcio event
        fun events() {
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        // funcio on click button
        findViewById<ImageButton>(R.id.btnMap).setOnClickListener {
            events()
        }
    }
}
