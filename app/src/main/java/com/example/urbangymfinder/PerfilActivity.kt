package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PerfilActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_activity);
        val TVname: TextView  = findViewById(R.id.txtName)
        val TVgender: TextView  = findViewById(R.id.txtGender)
        val TVphone: TextView  = findViewById(R.id.txtPhone)
        val TVemail: TextView  = findViewById(R.id.txtEmail)
        val TVaddress: TextView  = findViewById(R.id.txtAddress)

        mAuth = FirebaseAuth.getInstance()

        if (!user!!.isAnonymous) {
            // get user info
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val nombre: String? = documentSnapshot.getString("name")
                        TVname.setText(nombre)
                        val genero: String? = documentSnapshot.getString("gender")
                        TVgender.setText(genero)
                        val telefono: String? = documentSnapshot.getString("phone")
                        TVphone.setText(telefono)
                        val email: String? = documentSnapshot.getString("email")
                        TVemail.setText(email)
                        val direccio: String? = documentSnapshot.getString("address")
                        TVaddress.setText(direccio)
                    } else {
                        Log.d("Error", "could not reach data")
                    }
                }
        }

        fun back(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        fun  config(){
            val intent = Intent(this,ConfigActivity::class.java)
            startActivity(intent)
        }
        fun logout() {
            FirebaseAuth.getInstance().signOut()
            finish()
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            config()
        }
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            back()
        }

        findViewById<Button>(R.id.btnLogOut).setOnClickListener {
            logout()
        }
    }
}
