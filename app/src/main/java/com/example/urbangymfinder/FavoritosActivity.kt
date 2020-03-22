package com.example.urbangymfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FavoritosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }
    }
}
