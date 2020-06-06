package com.example.urbangymfinder

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

class FavoritosActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    val Any.TAG: String
        get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }
        getFavSpots()
    }

    // firebase get data
// firebase get data
    fun getFavSpots() {
        if (user!!.isEmailVerified) {
            // get favorits id fid
            val fid = db.collection("users/" + user.uid).document("favorits").get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
            // filter spots by fid

            val spots = db.collection("spots").whereIn("sid", listOf(fid)).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(TAG, "DocumentSnapshot data: ${document}")
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }

            // TODO populate recycle view or listview with favorits
        }
    }
}