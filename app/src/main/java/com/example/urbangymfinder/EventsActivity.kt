package com.example.urbangymfinder


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class EventsActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    lateinit var database: DatabaseReference
    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val listviewContent = ArrayList<String>()
        val sidList = ArrayList<String>()
        val titleList = ArrayList<String>()
        val directionList = ArrayList<String>()

        db.collection("events").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d(TAG, "${document.id} => ${document.data}")

                    sidList.add(document.getString("date").toString())
                    //descriptionList.add(document.getString("description").toString())
                    titleList.add(document.getString("name").toString())
                    //listviewContent.add(document.getString("date").toString() + "\n" +document.getString("name").toString()+ ":\n " +document.getString("description").toString())
                }
                listView = findViewById<ListView>(R.id.lvListaEvents)
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titleList)
                listView.adapter = adapter
                listView.setOnItemClickListener { parent, view, position, id ->
                    val element = parent.getItemAtPosition(position) // The item that was clicked
                    val intent = Intent(this, PopActivity::class.java)
                    intent.putExtra("nom", titleList[position])
                    startActivity(intent)
                }


            }
            .addOnFailureListener { exception ->
                Log.d("Error", "Error getting documents: ", exception)
        }

        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }
    }
}
/*
class EventsActivity : AppCompatActivity() {

    var eventsList = ArrayList<Event>()

    var event: Event = Event()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        //TODO add more events with json request to database
        /*
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
        */

        val EventsAdapter = EventsAdapter(this, eventsList)
        /*
        lvEvents.adapter = EventsAdapter
        lvEvents.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //TODO NEW ACTIVITY INTENT
            //Toast.makeText(this, "Want to see more information of " +
            //        eventsList[position].name + "event?", Toast.LENGTH_SHORT).show()
            eventDetailActivity(position)
        }
        
         */

        fun home(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnHome).setOnClickListener {
            home()
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
*
 */