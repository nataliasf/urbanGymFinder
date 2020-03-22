package com.example.urbangymfinder


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_events.*
import java.io.Serializable


import androidx.appcompat.app.AppCompatActivity


class EventsActivity : AppCompatActivity() {

    var eventsList = ArrayList<Event>()

    var event: Event = Event()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        //TODO add more events with json request to database

        eventsList.add(Event(
            "Massa Critica",
            "Thu, May 17, 10:00 AM",
            "10:00 to 12:00",
            "Plaça catalunya",
            "descripcio del event, participants, etc..." ))
        eventsList.add(Event(
            "Massa Critica 2",
            "Sat, May 19, 16:00 AM",
            "16:00 to 20:00",
            "Arc triomf",
            "Massive gathering of hundreds to march around the city of barcelona. Bring your bycicle!" ))
        eventsList.add(Event(
            "Yoga on the beach",
            "Thu, May 27, 8:00 AM",
            "8:00 - 11:00",
            "Bogatell",
            "Free yoga class in the beautiful Bogatell beach"  ))
        eventsList.add(Event(
            "CrossFit",
            "Fry, May 30, 18:00 AM",
            "18:00 - 21:00",
            "Park Güell",
            "descripcio del event, participants, etc..." ))
        eventsList.add(Event(
            "Massa Critica",
            "Thu, May 17, 10:00 AM",
            "10:00 to 12:00",
            "Plaça catalunya",
            "descripcio del event, participants, etc..." ))
        eventsList.add(Event(
            "Massa Critica 2",
            "Sat, May 19, 16:00 AM",
            "16:00 to 20:00",
            "Arc triomf",
            "Massive gathering of hundreds to march around the city of barcelona. Bring your bycicle!" ))
        eventsList.add(Event(
            "Yoga on the beach",
            "Thu, May 27, 8:00 AM",
            "8:00 - 11:00",
            "Bogatell",
            "descripcio del event, participants, etc..." ))
        eventsList.add(Event(
            "CrossFit",
            "Fry, May 30, 18:00 AM",
            "18:00 - 21:00",
            "Park Güell",
            "descripcio del event, participants, etc..." ))
        eventsList.add(Event(
            "Massa Critica",
            "Thu, May 17, 10:00 AM",
            "10:00 to 12:00",
            "Plaça catalunya",
            "descripcio del event, participants, etc..." ))
        eventsList.add(Event(
            "Massa Critica 2",
            "Sat, May 19, 16:00 AM",
            "16:00 to 20:00",
            "Arc triomf",
            "Massive gathering of hundreds to march around the city of barcelona. Bring your bycicle!" ))
        eventsList.add(Event(
            "Yoga on the beach",
            "Thu, May 27, 8:00 AM",
            "8:00 - 11:00",
            "Bogatell",
            "Free yoga class in the beautiful Bogatell beach" ))
        eventsList.add(Event(
            "CrossFit",
            "Fry, May 30, 18:00 AM",
            "18:00 - 21:00",
            "Park Güell",
            "descripcio del event, participants, etc..." ))
        /*
        val arrayAdapter: ArrayAdapter<*>
        //val eventsList = arrayOf("Massa critica: Wed, May 20, 9:00 PM", "Home runner: Sat, May 25, 5:00 PM", "Yoga on the beach: Thu, May 27, 8:00 AM")
        val eventsTitle = arrayOf("Massa critica", "Home runner", "Yoga on the beach")
        val date = arrayOf("Wed, May 20, 9:00 PM", "Sat, May 25, 5:00 PM", "Thu, May 27, 8:00 AM")
        val location = arrayOf("Arc del Triumf", "Plaça Catalunya", "Bogatell")
        val description = arrayOf(
            "Massive gathering of hundreds to march around the city of barcelona. Bring your bycicle!",
            "Baseball game in Plaça Catalunya, everyone is welcome to join!",
            "Free yoga class in the beautiful Bogatell beach")

         */
        var EventsAdapter = EventsAdapter(this, eventsList)
        lvEvents.adapter = EventsAdapter
        lvEvents.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //TODO NEW ACTIVITY INTENT
            Toast.makeText(this, "Want to see more information of " +
                    eventsList[position].name + "event?", Toast.LENGTH_SHORT).show()
            eventDetailActivity(position);
        }


    }

    fun eventDetailActivity(position: Int){
        val intent = Intent(this, EventdetailActivity::class.java)
        event = eventsList.get(position)
        intent.putExtra("event", event as Serializable)
        startActivity(intent)
    }


    inner class EventsAdapter : BaseAdapter {

        private var eventsList = ArrayList<Event>()
        private var context: Context? = null

        constructor(context: Context, eventsList: ArrayList<Event>) : super() {
            this.eventsList = eventsList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.event, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.eventName.text = eventsList[position].name
            vh.eventLocation.text = eventsList[position].location
            vh.eventDate.text = eventsList[position].date

            return view
        }

        override fun getItem(position: Int): Any {
            return eventsList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return eventsList.size
        }
    }

    private class ViewHolder(view: View?) {
        val eventName: TextView
        val eventLocation: TextView
        val eventDate: TextView

        init {
            this.eventName = view?.findViewById(R.id.eventName) as TextView
            this.eventLocation = view?.findViewById(R.id.eventLocation) as TextView
            this.eventDate = view?.findViewById(R.id.eventDate) as TextView
        }
    }

}
/*
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import android.widget.ArrayAdapter
import android.widget.ListView
class EventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        val arrayAdapter: ArrayAdapter<*>
        //val eventsList = arrayOf("Massa critica: Wed, May 20, 9:00 PM", "Home runner: Sat, May 25, 5:00 PM", "Yoga on the beach: Thu, May 27, 8:00 AM")
        val eventsTitle = arrayOf("Massa critica", "Home runner", "Yoga on the beach")
        val date = arrayOf("Wed, May 20, 9:00 PM", "Sat, May 25, 5:00 PM", "Thu, May 27, 8:00 AM")
        val location = arrayOf("Arc del Triumf", "Plaça Catalunya", "Bogatell")
        val description = arrayOf(
            "Massive gathering of hundreds to march around the city of barcelona. Bring your bycicle!",
            "Baseball game in Plaça Catalunya, everyone is welcome to join!",
            "Free class of yoga in the beautiful Bogatell beach")

        // access the listView from xml file
        var mListView = findViewById<ListView>(R.id.eventsTitle)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_2, eventsTitle)
        mListView.adapter = arrayAdapter

        val context = this
        mListView.setOnItemClickListener { _, _, position, _ ->
            val title = eventsTitle[position]
            val date = date[position]
            val location = location[position]
            val description = description[position]
            val intent = Intent(this, EventdetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("date", date)
            intent.putExtra("location", location)
            intent.putExtra("description", description)
            startActivity(intent)
        }
    }
}
*/