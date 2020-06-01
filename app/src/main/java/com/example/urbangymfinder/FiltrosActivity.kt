package com.example.urbangymfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class FiltrosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtros)

        val spinnerWhere : Spinner = findViewById(R.id.spinnerLugares)
        val spinnerKind : Spinner = findViewById(R.id.spinnerTipos)

        findViewById<Button>(R.id.buttonok).setOnClickListener {
            Toast.makeText(this, "Showing new results", Toast.LENGTH_LONG).show()
            save()
        }

        var spinnerLugaresList = ArrayList<String>()
        spinnerLugaresList.add("Less than 10km away")
        spinnerLugaresList.add("Less than 5km away")
        spinnerLugaresList.add("Less than 1km away")
        spinnerLugaresList.add("Any distance")

        var adapterLugares = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, spinnerLugaresList)

        adapterLugares.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerWhere.adapter = adapterLugares

        var spinnerTiposList = ArrayList<String>()
        spinnerTiposList.add("Outdoors")
        spinnerTiposList.add("Indoors")
        spinnerTiposList.add("Any kind")

        var adapterTipos = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, spinnerTiposList)

        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKind.adapter = adapterTipos
        
    }
    //funcions navegació activity
    fun save() {
        finish()
    }

    // TO DO: Implementar una funcion al presionar el boton que te lleve al mapa con los filtros marcados
}
