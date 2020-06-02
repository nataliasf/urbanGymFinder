package com.example.urbangymfinder

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class PopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var database: DatabaseReference;
        val db = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop)

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

    }

}
