package com.example.urbangymfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class FavoritosActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    lateinit var database: DatabaseReference
    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val listviewContent = ArrayList<String>()
        val sidList = ArrayList<String>()
        val titleList = ArrayList<String>()
        val directionList = ArrayList<String>()
        val descriptionList = ArrayList<String>()
        if (!user!!.isAnonymous) {
            db.collectionGroup("spots").whereEqualTo("followerID", user.uid).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        //Log.d(TAG, "${document.id} => ${document.data}")

                        sidList.add(document.getString("sid").toString())
                        titleList.add(document.getString("Title").toString())
                        descriptionList.add(document.getString("Description").toString())
                        directionList.add(document.getString("direccion").toString())
                        listviewContent.add(document.getString("Title").toString()+ ":\n "  + document.getString("direccion").toString())
                    }
                    listView = findViewById<ListView>(R.id.lvListaFavs)
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listviewContent)
                    listView.adapter = adapter
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val element = parent.getItemAtPosition(position) // The item that was clicked
                        val intent = Intent(this, PopActivity::class.java)
                        intent.putExtra("nom", titleList[position])
                        intent.putExtra("descripcio", descriptionList[position])
                        startActivity(intent)
                    }


                }
                .addOnFailureListener { exception ->
                    Log.d("Error", "Error getting documents: ", exception)
                }

        }else{
            Toast.makeText(
                applicationContext,
                "Register with an email to use your favorite list",
                Toast.LENGTH_LONG
            ).show()

        }
        findViewById<Button>(R.id.buttonback).setOnClickListener {
            finish()
        }



    }
}
        /*
        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext,
                "TODO implement navigation to event detail",
                Toast.LENGTH_LONG ).show()

            val intent = Intent(this, PopActivity::class.java)
            intent.putExtra("spotID", sidList[position])
            startActivity(intent)
        }
         */


// TODO populate recycle view or listview with favorits

        /*
        if (!user!!.isAnonymous) {
            db.collection("spots").whereEqualTo("userID", true).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        //Log.d(TAG, "${document.id} => ${document.data}")
                        sidList.add(document.getString("sid").toString())
                        titleList.add(document.getString("Title").toString())
                        descriptionList.add(document.getString("Description").toString())
                    }
                        .addOnFailureListener { exception ->
                            Log.d("Error", "Error getting documents: ", exception)
                        }

                }


        }
         */

        /*
        if (!user!!.isAnonymous) {
            db.collection("spots").get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        //Log.d(TAG, "${document.id} => ${document.data}")
                        sidList.add(document.getString("sid").toString())
                        titleList.add(document.getString("Title").toString())
                        descriptionList.add(document.getString("Description").toString())
                    }
                    listView = findViewById<ListView>(R.id.lvListaFavs)
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titleList)
                    listView.adapter = adapter
                }
                .addOnFailureListener { exception ->
                    Log.d("Error", "Error getting documents: ", exception)
                }

        }

         */




        /*
        if (!user!!.isAnonymous) {
            // get user info
            db.collection("users/"+ user.uid).document("favorits").get()
                .addOnCompleteListener { documents ->
                    if (documents.isSuccessful) {
                        val list = ArrayList<String>()
                            val name = documents.toArrayList()
                            list.add(name)
                        }
                        db.collection("spots").whereIn("sid", list).get()
                            .addOnCompleteListener { task2 ->
                                if (task2.isSuccessful) {
                                    val list = ArrayList<String>()
                                    for (document in task2.result) {
                                        val name = document.data["name"].toString()
                                        list.add(name)
                                    }
                                }
                            }
                    }
                }

        }

         */

        /*
        val listItems = arrayOfNulls<String>(favSpots)
        for (i in 0 until recipeList.size) {
            val recipe = recipeList[i]
            listItems[i] = recipe.title
        }

         */

        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        //listView.adapter = adapter



    // firebase get data
// firebase get data
/*
    fun getFavSpots() {
        if (user!!.isEmailVerified) {
            // get favorits id fid
            val fid = db.collection("users/" + user.uid).document("favorits").get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("Error", "DocumentSnapshot data: ${document.data}")
                    } else {
                        Log.d("Error", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Error", "get failed with ", exception)
                }
            // filter spots by fid

            val spots = db.collection("spots").whereIn("sid", listOf(fid)).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("Error", "DocumentSnapshot data: ${document}")
                    } else {
                        Log.d( "Error", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
            Toast.makeText(this, "The list " +
                    spots + " has been get!", Toast.LENGTH_LONG).show()

        }
    }
}

 */