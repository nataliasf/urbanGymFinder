package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //testing database.
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")


        // funcions on click btns
        findViewById<ImageButton>(R.id.btnEvents).setOnClickListener {
            Toast.makeText(this, "This is the list of new events!", Toast.LENGTH_LONG).show()
            events()
        }

        findViewById<ImageButton>(R.id.btnFavorites).setOnClickListener {
            Toast.makeText(this, "This is your list of favorit places!", Toast.LENGTH_LONG).show()
            favorites()
        }
        findViewById<ImageButton>(R.id.btnProfile).setOnClickListener {
            Toast.makeText(this, "Profile selected!", Toast.LENGTH_LONG).show()
            profile()
        }
        findViewById<ImageButton>(R.id.btnHome).setOnClickListener {
            Toast.makeText(this, "Select home/config!", Toast.LENGTH_LONG).show()
            home()
        }
        findViewById<ImageButton>(R.id.btnMap).setOnClickListener {
            Toast.makeText(this, "Select map option!", Toast.LENGTH_LONG).show()
            map()
        }
        findViewById<ImageButton>(R.id.btnFilters).setOnClickListener {
            Toast.makeText(this, "Select filters!", Toast.LENGTH_LONG).show()
            filters()
        }
    }
    //funcions navegació activity
    fun events() {
        val intent = Intent(this, EventsActivity::class.java)
        startActivity(intent)
    }
    fun favorites() {
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }
    fun profile() {
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }
    fun home() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }
    fun map() {
        val intent = Intent(this, Mapa::class.java)
        startActivity(intent)
    }
    fun filters() {
        val intent = Intent(this, FiltrosActivity::class.java)
        startActivity(intent)
    }
}
