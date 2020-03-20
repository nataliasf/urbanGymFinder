package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser:EditText
    private lateinit var txtPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        txtUser = findViewById(R.id.et_email)
        txtPassword = findViewById(R.id.et_password)

        findViewById<Button>(R.id.btnLogin).setOnClickListener{loginUser()}

    }

    private fun loginUser(){
        val user: String = txtUser.text.toString()
        val password: String = txtUser.text.toString()

        val intent = Intent(this, MainActivity::class.java).also { it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) }

        startActivity(intent)

    }

}
