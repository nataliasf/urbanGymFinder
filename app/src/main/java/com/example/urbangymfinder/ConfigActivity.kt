package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {

    private var changeBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config_activity)

        initializeUI()
        changeBtn?.setOnClickListener { resetUserPassword() }


    fun save(){


        Toast.makeText(this, "Changes saved!", Toast.LENGTH_LONG).show()
        finish()



    }
    findViewById<Button>(R.id.btnSave).setOnClickListener {
        save()
    }

    fun back(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
    }

     findViewById<Button>(R.id.btnBack2).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.btnBack2).setOnClickListener {
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




