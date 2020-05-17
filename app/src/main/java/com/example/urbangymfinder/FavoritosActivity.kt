package com.example.urbangymfinder

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

class FavoritosActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }
    }

    // firebase get data
    fun getFavSpots() {
        db.collection("spots").whereEqualTo("favorit", true).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nombre: String? = document.getString("Title")

                    // primer exemple basic
                    // TODO fer llista de spots favorits

                }
            }
    }
}
