package com.example.urbangymfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FiltrosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtros)

        findViewById<Button>(R.id.buttonok).setOnClickListener {
            Toast.makeText(this, "Showing new results", Toast.LENGTH_LONG).show()
            finish()
        }

        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }
    }

}
