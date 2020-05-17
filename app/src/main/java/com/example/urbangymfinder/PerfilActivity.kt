package com.example.urbangymfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent;
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.perfil_activity.*

class PerfilActivity : AppCompatActivity() {


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_activity);


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
