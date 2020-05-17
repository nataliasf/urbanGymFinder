package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pop_activity.*


class PopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var database: DatabaseReference;
        val db = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_activity)

        findViewById<Button>(R.id.buttonfavs).setOnClickListener {
            //Añadir a favoritos
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                // User is signed in

                // Recuperar los gimnasios preferidos de la bd
                // Añadir a esos el gimnasio en cuestión
                // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                //db.collection("users").

                Toast.makeText(
                    this,
                    "Spot added to favorites",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                // No user is signed in
                Toast.makeText(
                    this,
                    "You must be signed in to add a spot to your favorites",
                    Toast.LENGTH_LONG
                ).show()
            }



        }


    }

}
