package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mapa.*

class Mapa : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);


   fun back(){
       finish()
   }

    findViewById<Button>(R.id.btnReturn).setOnClickListener {
        back()
    }
}

}