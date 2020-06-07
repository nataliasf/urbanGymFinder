package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    /*private lateinit var txtUser:EditText
    private lateinit var txtPassword:EditText
*/

    private var emailTV: EditText? = null
    private var passwordTV: EditText? = null
    private var resetBtn: Button? = null
    private var loginBtn: Button? = null
    private var registerBtn: Button? = null
    private var guestBtn: Button? = null
    //private var progressBar: ProgressBar? = null


    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        mAuth = FirebaseAuth.getInstance()

        //chech if user is login
        val user = mAuth!!.currentUser
        if (user != null) {
            if( !user.isAnonymous) {
                // User is signed in go to mainActivity
                Toast.makeText(
                    applicationContext,
                    "Welcome back " + user.displayName + "!",
                    Toast.LENGTH_LONG
                ).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)


        } else {
            Toast.makeText(
                applicationContext,
                "Welcome to Urban Gym Finder!",
                Toast.LENGTH_LONG
            ).show()
        }
        initializeUI()

        loginBtn?.setOnClickListener { loginUserAccount() }
        registerBtn?.setOnClickListener { registerUserAccount() }
        guestBtn?.setOnClickListener { loginUserGuest() }
        resetBtn?.setOnClickListener { resetUserPassword() }
    }

    //TODO signOut Firebase.auth.signOut()
    //TODO register from anon signin

    private fun loginUserGuest(){
        mAuth!!.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Welcome as a guest! You can register later",
                        Toast.LENGTH_LONG
                    ).show()
                    //progressBar.visibility = View.GONE
                    val intent =
                        Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                    //progressBar.visibility = View.GONE
                }
            }
    }

    private fun resetUserPassword(){
        val intent =
            Intent(this@LoginActivity, ResetActivity::class.java)
        startActivity(intent)
    }


    private fun registerUserAccount(){
        val intent =
            Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun loginUserAccount() {
        //progressBar!!.visibility = View.VISIBLE
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
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Login successful!",
                        Toast.LENGTH_LONG
                    ).show()
                    //progressBar.visibility = View.GONE
                    val intent =
                        Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun initializeUI() {
        emailTV = findViewById(R.id.et_email)
        passwordTV = findViewById(R.id.et_password)
        loginBtn = findViewById(R.id.btnLogin)
        registerBtn = findViewById(R.id.btnRegister)
        guestBtn = findViewById(R.id.btnGuest)
        resetBtn = findViewById(R.id.btnForgotPass)
    }
}





