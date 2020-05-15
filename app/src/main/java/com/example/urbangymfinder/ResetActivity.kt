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



class ResetActivity : AppCompatActivity()  {
    /* private lateinit var textEmail: EditText
     private lateinit var textPassword:EditText
     private lateinit var textName:EditText
     private lateinit var btnRegistrar:Button
 */

    private var emailTV: EditText? = null
    private var resetBtn: Button? = null
    private var backBtn: Button? = null

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        auth = FirebaseAuth.getInstance()

        initializeUI()

        resetBtn?.setOnClickListener { resetPassword() }
        backBtn?.setOnClickListener { back() }

    }

    private fun back() {
        val intent =
            Intent(this@ResetActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun resetPassword() {
        val email: String
        email = emailTV!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter your email...", Toast.LENGTH_LONG)
                .show()
            return
        }
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener({ task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Password reset mail sent successful! Check your mail for futher instructions",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent =
                        Intent(this@ResetActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password reset failed! Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }



    private fun initializeUI() {
        emailTV = findViewById(R.id.etEmail)
        resetBtn = findViewById(R.id.resetBtn)
        backBtn = findViewById(R.id.backBtn)
    }
}

