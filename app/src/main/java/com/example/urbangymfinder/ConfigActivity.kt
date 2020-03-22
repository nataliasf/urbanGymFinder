package com.example.urbangymfinder

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            Toast.makeText(this, "Changes saved!", Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.btnBack2).setOnClickListener {
            finish()
        }
    }

}