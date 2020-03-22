package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config_activity)



    fun save(){
        val intent = Intent(this,PerfilActivity::class.java)
        startActivity(intent)



    }
    findViewById<Button>(R.id.btnSave).setOnClickListener {
        save()
    }

    fun back(){
            val intent = Intent(this,PerfilActivity::class.java)
            startActivity(intent)



    }
     findViewById<Button>(R.id.btnSave).setOnClickListener {
            back()
        }

}


}




