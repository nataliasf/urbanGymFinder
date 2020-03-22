package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class LoginActivity : AppCompatActivity() {

    // exemple var nom usuari del login per pasar a seguent activity
    var userName: String = "runnerConfinat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        //funcio login
        fun login() {
            val intent = Intent(this, EventsActivity::class.java)
            intent.putExtra("userName", userName)
            startActivity(intent)
        }

        // funcio on click button
        findViewById<Button>(R.id.logInBtn).setOnClickListener {
            login()
        }
    }
}
