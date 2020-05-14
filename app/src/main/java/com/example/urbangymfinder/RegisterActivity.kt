package com.example.urbangymfinder


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth



class RegisterActivity : AppCompatActivity()  {
    /* private lateinit var textEmail: EditText
     private lateinit var textPassword:EditText
     private lateinit var textName:EditText
     private lateinit var btnRegistrar:Button
 */

    private var emailTV: EditText? = null
    private  var passwordTV:EditText? = null
    private var regBtn: Button? = null
    private var progressBar: ProgressBar? = null

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        initializeUI()

        regBtn?.setOnClickListener { registerNewUser() }


    }
    private fun registerNewUser() {
        progressBar!!.visibility = View.VISIBLE
        val email: String
        val password: String
        email = emailTV!!.text.toString()
        password = passwordTV!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email...", Toast.LENGTH_LONG)
                .show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_LONG)
                .show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener({ task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Registration successful!",
                        Toast.LENGTH_LONG
                    ).show()
                    progressBar!!.visibility = View.GONE
                    val intent =
                        Intent(this@RegisterActivity, LoginActivity::class.java) //segons d'on ho he tret hauria de ser this@activity_register pero dona error
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Registration failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                    progressBar!!.visibility = View.GONE
                }
            })
    }



    private fun initializeUI() {
        emailTV = findViewById(R.id.etEmail)
        passwordTV = findViewById(R.id.etPassword)
        regBtn = findViewById(R.id.buttonok)
        //progressBar = findViewById(R.id.progressBar);
    }
}


