package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConfigActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    lateinit var TVname : EditText
    lateinit var TVgender: EditText
    lateinit var TVphone: EditText
    lateinit var TVemail: EditText
    lateinit var TVaddress: EditText

    lateinit var name : String
    lateinit var gen: String
    lateinit var phone: String
    lateinit var email: String
    lateinit var add: String

    private var changeBtn: Button? = null

    val Any.TAG: String
        get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config_activity)



        initializeUI()
        changeBtn?.setOnClickListener { resetUserPassword() }


        fun dec(){
            TVname = findViewById(R.id.etName)
            TVgender = findViewById(R.id.etGender)
            TVphone = findViewById(R.id.etPhone)
            TVemail  = findViewById(R.id.etEmail)
            TVaddress = findViewById(R.id.etAdd)

            name=TVname.text.toString()
            gen = TVgender.text.toString()
            phone = TVphone.text.toString()
            email = TVemail.text.toString()
            add = TVaddress.text.toString()
        }
        fun save() {
            dec()
            /*val obj=findViewById<EditText>(R.id.etName)
            Toast.makeText(this,obj.text, Toast.LENGTH_LONG).show()*/
            if (!user!!.isAnonymous) {
                if(name!=""){
                db.collection("users").document(user.uid)
                 .update(
                     "name", name
                 )
                }
             if(gen!=""){
                            db.collection("users").document(user.uid)
                                .update(
                                    "gender",gen
                                )
                        }

             if(phone!=""){
                 db.collection("users").document(user.uid)
                     .update(
                         "phone", phone
                     )
                     .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                     .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e)}
             }
            if(email!=""){
                db.collection("users").document(user.uid)
                    .update(
                        "email", email
                    )
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e)}
            }
           if(add!=""){
               db.collection("users").document(user.uid)
                   .update(
                       "address", add)
                   .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                   .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e)}
           }


            }
        Toast.makeText(this, "Changes saved!", Toast.LENGTH_LONG).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
    }

    findViewById<Button>(R.id.btnSave).setOnClickListener {
        save()
    }


    fun back(){
            val intent = Intent(this,PerfilActivity::class.java)
            startActivity(intent)
    }

     findViewById<Button>(R.id.btnBack2).setOnClickListener {
            back()
        }
        findViewById<Button>(R.id.btnChange).setOnClickListener {
            resetUserPassword()
        }
}
    private fun resetUserPassword(){
        val intent =
            Intent(this@ConfigActivity, ResetActivity::class.java)
        startActivity(intent)
    }
    private fun initializeUI() {
        changeBtn = findViewById(R.id.btnChange)
    }
}




