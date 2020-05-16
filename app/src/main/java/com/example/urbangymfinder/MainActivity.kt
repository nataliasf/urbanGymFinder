package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference;
    lateinit var user: String


    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtTitle1: TextView= findViewById(R.id.txttitle1)
        val txtDirection1: TextView = findViewById(R.id.txtdirection1)

        val txtTitle2: TextView= findViewById(R.id.txttitle2)
        val txtDirection2: TextView = findViewById(R.id.txtdirection2)

        val txtTitle3: TextView= findViewById(R.id.txttitle3)
        val txtDirection3: TextView = findViewById(R.id.txtdirection3)

        val txtTitle4: TextView= findViewById(R.id.txttitle4)
        val txtDirection4: TextView = findViewById(R.id.txtdirection4)

        val txtTitle5: TextView= findViewById(R.id.txttitle5)
        val txtDirection5: TextView = findViewById(R.id.txtdirection5)

        val txtTitle6: TextView= findViewById(R.id.txttitle6)
        val txtDirection6: TextView = findViewById(R.id.txtdirection6)

        val txtTitle7: TextView= findViewById(R.id.txttitle6)
        val txtDirection7: TextView = findViewById(R.id.txtdirection6)

        getFirebaseData(txtTitle1,txtDirection1, "1")
        getFirebaseData(txtTitle2, txtDirection2, "2")
        getFirebaseData(txtTitle3, txtDirection3, "3")
        getFirebaseData(txtTitle4, txtDirection4, "4")
        getFirebaseData(txtTitle5, txtDirection5, "5")
        getFirebaseData(txtTitle6, txtDirection6, "6")
        getFirebaseData(txtTitle7, txtDirection7, "7")




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

    fun getFirebaseData(txt_title: TextView, txt_dir: TextView, documentPath: String){

        db.collection("spots").document(documentPath).get().addOnSuccessListener { documentSnapshot ->
            if(documentSnapshot.exists()){
                val nombre: String? = documentSnapshot.getString("Title")
                txt_title.setText(nombre)

                val dir: String? = documentSnapshot.getString("direccion")
                txt_dir.setText(dir)
            }else{
                Log.d("Error", "could not reach data")
            }

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
