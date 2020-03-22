

package com.example.urbangymfinder

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity



class EventdetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventdetail)


        var event = Event()
        if(getIntent() != null && getIntent().getExtras() != null){
            event = getIntent().getSerializableExtra("event") as Event
        }
        //TODO all values and layout

        findViewById<TextView>(R.id.eventName).setText(event.name)
        findViewById<TextView>(R.id.eventDate).setText(event.date)
        findViewById<TextView>(R.id.eventLocation).setText(event.location)
        findViewById<TextView>(R.id.eventDescription).setText(event.description)

        findViewById<Button>(R.id.confirmEvent).setOnClickListener {
            Toast.makeText(this, "The event " +
                    event.name + " has been added!", Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.backEvent).setOnClickListener {
            finish()
        }
    }
}
