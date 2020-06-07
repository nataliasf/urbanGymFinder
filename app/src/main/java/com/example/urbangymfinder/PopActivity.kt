package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class PopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var database: DatabaseReference;
        val db = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop)

        // TODO set popTitle and popDescription if intent.getExtra !!
        val intent: Intent = intent
        val titol = intent.getData("nom")
        val descripcio = intent.getData("descripcio")
        if ( titol != null) {
            val txtTitle: TextView = findViewById(R.id.popDescription)
            txtTitle.setText(titol)
        }
        if ( descripcio != null){
            val txtDescription: TextView = findViewById(R.id.textDescription)
            txtDescription.setText(descripcio)
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
    fun Intent.getData(key: String): String {
        return extras?.getString(key) ?: "intent is null"
    }

}
