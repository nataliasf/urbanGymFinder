package com.example.urbangymfinder

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.activity_pop_activity.*


class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference;
    lateinit var user: String


    val db = FirebaseFirestore.getInstance()


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtTitle1: TextView= findViewById(R.id.txttitle1)
        val txtDirection1: TextView = findViewById(R.id.txtdirection1)


        val txtTitle2: TextView= findViewById(R.id.txttitle2)
        val txtDirection2: TextView = findViewById(R.id.txtdirection2)

        val txtTitle3: TextView= findViewById(R.id.txttitle3)
        val txtDirection3: TextView = findViewById(R.id.txtdirection3)

        val txtTitle4: TextView= findViewById(R.id.txttitle4)
        val txtDirection4: TextView = findViewById(R.id.txtdirection4)

        val txtTitle5: TextView= findViewById(R.id.txttitle5)

        val txtDirection5: TextView = findViewById(R.id.txtdirection5)

        val txtTitle6: TextView= findViewById(R.id.txttitle6)
        val txtDirection6: TextView = findViewById(R.id.txtdirection6)

        val txtTitle7: TextView= findViewById(R.id.txttitle6)
        val txtDirection7: TextView = findViewById(R.id.txtdirection6)

        getFirebaseData(txtTitle1,txtDirection1, "1")
        getFirebaseData(txtTitle2, txtDirection2, "2")
        getFirebaseData(txtTitle3, txtDirection3, "3")
        getFirebaseData(txtTitle4, txtDirection4, "4")
        getFirebaseData(txtTitle5, txtDirection5, "5")
        getFirebaseData(txtTitle6, txtDirection6, "6")
        getFirebaseData(txtTitle7, txtDirection7, "7")


        //CLICK LISTENERS FOR SPOT DETAILS


        txtTitle1.setOnClickListener{
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //val pop_view = inflater.inflate(R.layout.activity_pop_activity, null)


            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle1.text)
            getFireBaseDescription(pop_description, "1")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                getFirebaseData(txtTitle1,txtDirection1, "1")
                getFirebaseData(txtTitle2, txtDirection2, "2")
                getFirebaseData(txtTitle3, txtDirection3, "3")
                getFirebaseData(txtTitle4, txtDirection4, "4")
                getFirebaseData(txtTitle5, txtDirection5, "5")
                getFirebaseData(txtTitle6, txtDirection6, "6")
                getFirebaseData(txtTitle7, txtDirection7, "7")
                // Dismiss the detail window
                setContentView(R.layout.activity_main)
                //Set data from Firebase

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            //Set a click listener for show on Map Button
            //              ****** AQUI S'HA DE LINKEJAR MAP ***********




            /*val popupWindow = PopupWindow(
                pop_view, //view itself
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of popup w
                RelativeLayout.LayoutParams.WRAP_CONTENT // Window height
            )

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            // If API level 23 or higher then execute the code
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut
            }

            // Get the widgets reference from custom view
            val pop_title = pop_view.findViewById<TextView>(R.id.popTitle)
            val pop_description = pop_view.findViewById<TextView>(R.id.popDescription)
            val pop_btnBack = pop_view.findViewById<Button>(R.id.btnBack)


            // Set a click listener for popup's button widget
            btnBack.setOnClickListener{
                // Dismiss the popup window
                popupWindow.dismiss()
            }

            //Set a click listener for show on Map Button
            //              ****** AQUI S'HA DE LINKEJAR MAP ***********


            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                root_layout, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
            )*/
        }

        txtTitle2.setOnClickListener{

            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle2.text)
            getFireBaseDescription(pop_description, "2")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                // Dismiss the detail window
                setContentView(R.layout.activity_main)

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        txtTitle3.setOnClickListener{

            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            // * Posar com a variables globals.. *
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle3.text)
            getFireBaseDescription(pop_description, "3")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                // Dismiss the detail window
                setContentView(R.layout.activity_main)

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        txtTitle4.setOnClickListener{

            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle4.text)
            getFireBaseDescription(pop_description, "4")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                // Dismiss the detail window
                setContentView(R.layout.activity_main)

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        txtTitle5.setOnClickListener{

            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle5.text)
            getFireBaseDescription(pop_description, "5")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                // Dismiss the detail window
                setContentView(R.layout.activity_main)

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        txtTitle6.setOnClickListener{

            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle6.text)
            getFireBaseDescription(pop_description, "6")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                // Dismiss the detail window
                setContentView(R.layout.activity_main)

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        txtTitle7.setOnClickListener{

            setContentView(R.layout.activity_pop_activity)

            // Get the widgets reference from custom view
            val pop_title: TextView = findViewById(R.id.popTitle)
            val pop_description = findViewById<TextView>(R.id.popDescription)


            pop_title.setText(txtTitle7.text)
            getFireBaseDescription(pop_description, "7")

            // Set a click listener for pop_activity btn
            btnBack.setOnClickListener{
                // Dismiss the detail window
                setContentView(R.layout.activity_main)

            }

            //TODO get spotID on mapActivity, zoom in and show title
            btnShowMap.setOnClickListener({
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("spotID", "test")
                startActivity(intent)
            })

            //TODO add to database user/favorites for each element
            buttonfavs.setOnClickListener {
                //Añadir a favoritos
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // User is signed in

                    // Recuperar los gimnasios preferidos de la bd
                    // Añadir a esos el gimnasio en cuestión
                    // Guardar el nuevo valor en db.collection("users") del usuario en cuestión

                    // Update favorit field

                    val data = hashMapOf("favorit" to true)
                    db.collection("spots").document("test")
                        .set(data, SetOptions.merge())
                    Toast.makeText(
                        this,
                        "Spot added to favorites",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    // No user is signed in
                    Toast.makeText(
                        this,
                        "You must be signed in to add a spot to your favorites",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        // funcions on click btns
        findViewById<ImageButton>(R.id.btnEvents).setOnClickListener {
            Toast.makeText(this, "This is the list of new events!", Toast.LENGTH_LONG).show()
            events()
        }

        findViewById<ImageButton>(R.id.btnFavorites).setOnClickListener {
            Toast.makeText(this, "This is your list of favorit places!", Toast.LENGTH_LONG).show()
            favorites()
        }
        findViewById<ImageButton>(R.id.btnProfile).setOnClickListener {
            Toast.makeText(this, "Profile selected!", Toast.LENGTH_LONG).show()
            profile()
        }
        findViewById<ImageButton>(R.id.btnHome).setOnClickListener {
            Toast.makeText(this, "Select home/config!", Toast.LENGTH_LONG).show()
            home()
        }
        findViewById<ImageButton>(R.id.btnMap).setOnClickListener {
            Toast.makeText(this, "Select map option!", Toast.LENGTH_LONG).show()
            map()
        }
        findViewById<ImageButton>(R.id.btnFilters).setOnClickListener {
            Toast.makeText(this, "Select filters!", Toast.LENGTH_LONG).show()
            filters()
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        val txtTitle1: TextView= findViewById(R.id.txttitle1)
        val txtDirection1: TextView = findViewById(R.id.txtdirection1)


        val txtTitle2: TextView= findViewById(R.id.txttitle2)
        val txtDirection2: TextView = findViewById(R.id.txtdirection2)

        val txtTitle3: TextView= findViewById(R.id.txttitle3)
        val txtDirection3: TextView = findViewById(R.id.txtdirection3)

        val txtTitle4: TextView= findViewById(R.id.txttitle4)
        val txtDirection4: TextView = findViewById(R.id.txtdirection4)

        val txtTitle5: TextView= findViewById(R.id.txttitle5)

        val txtDirection5: TextView = findViewById(R.id.txtdirection5)

        val txtTitle6: TextView= findViewById(R.id.txttitle6)
        val txtDirection6: TextView = findViewById(R.id.txtdirection6)

        val txtTitle7: TextView= findViewById(R.id.txttitle6)
        val txtDirection7: TextView = findViewById(R.id.txtdirection6)


        getFirebaseData(txtTitle1,txtDirection1, "1")
        getFirebaseData(txtTitle2, txtDirection2, "2")
        getFirebaseData(txtTitle3, txtDirection3, "3")
        getFirebaseData(txtTitle4, txtDirection4, "4")
        getFirebaseData(txtTitle5, txtDirection5, "5")
        getFirebaseData(txtTitle6, txtDirection6, "6")
        getFirebaseData(txtTitle7, txtDirection7, "7")
    }



    fun getFirebaseData(txt_title: TextView, txt_dir: TextView, documentPath: String){

        db.collection("spots").document(documentPath).get().addOnSuccessListener { documentSnapshot ->
            if(documentSnapshot.exists()){
                val nombre: String? = documentSnapshot.getString("Title")
                txt_title.setText(nombre)

                val dir: String? = documentSnapshot.getString("direccion")
                txt_dir.setText(dir)

            }else{
                Log.d("Error", "could not reach data")
            }

        }

    }

    fun getFireBaseDescription(txt_description: TextView, documentPath: String) {
        db.collection("spots").document(documentPath).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val description: String? = documentSnapshot.getString("Description")
                    txt_description.setText(description)

                }
            }
    }



    //funcions navegació activity
    fun events() {
        val intent = Intent(this, EventsActivity::class.java)
        startActivity(intent)
    }
    fun favorites() {
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }
    fun profile() {
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }
    fun home() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }
    fun map() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
    fun filters() {
        val intent = Intent(this, FiltrosActivity::class.java)
        startActivity(intent)
    }
}
