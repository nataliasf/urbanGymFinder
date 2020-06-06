package com.example.urbangymfinder


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity()  {
    /* private lateinit var textEmail: EditText
     private lateinit var textPassword:EditText
     private lateinit var textName:EditText
     private lateinit var btnRegistrar:Button
 */
    val Any.TAG: String
        get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }

    private var emailTV: EditText? = null
    private  var passwordTV:EditText? = null
    private var nameTV: EditText? = null
    private var regBtn: Button? = null
    private var progressBar: ProgressBar? = null

    private lateinit var auth:FirebaseAuth

    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        initializeUI()

        regBtn?.setOnClickListener { registerNewUser() }

        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }


    }
    private fun registerNewUser() {
        //progressBar!!.visibility = View.VISIBLE
        val email: String
        val password: String
        val name: String
        email = emailTV!!.text.toString()
        password = passwordTV!!.text.toString()
        name = nameTV!!.text.toString()
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
                        "Registration successful! Welcome",
                        Toast.LENGTH_LONG
                    ).show()
                    // database create new user
                    // withe classes, map or dict
                    // https://firebase.google.com/docs/firestore/manage-data/add-data#kotlin+ktx_2
                    val docData = hashMapOf(
                        "email" to email,
                        "name" to name,
                        "admin" to false,
                        "friends" to arrayListOf<String>(),
                        "favorits" to arrayListOf<String>(),
                        "events" to arrayListOf<String>()
                    )


                    db.collection("users").document(email)
                        .set(docData)
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e)}

                            //progressBar!!.visibility = View.GONE
                    val intent =
                        Intent(this@RegisterActivity, MainActivity::class.java) //segons d'on ho he tret hauria de ser this@activity_register pero dona error
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Registration failed! Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                    //progressBar!!.visibility = View.GONE
                }
            })
    }



    private fun initializeUI() {
        emailTV = findViewById(R.id.etEmail)
        passwordTV = findViewById(R.id.etPassword)
        nameTV = findViewById(R.id.etName)
        regBtn = findViewById(R.id.buttonok)
        //progressBar = findViewById(R.id.progressBar);
    }
}