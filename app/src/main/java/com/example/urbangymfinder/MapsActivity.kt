package com.example.urbangymfinder

import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    lateinit var database: DatabaseReference;
    val db = FirebaseFirestore.getInstance()

    private lateinit var map: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

        // 3
        private const val REQUEST_CHECK_SETTINGS = 2
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap
        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
        getAllSpotsOnMap()

        // Add a marker in Barcelona and move the camera
        //41°23'11.4"N 2°09'49.3"E
        // primer exemple basic
        /*
        val ub = LatLng(41.387, 2.164)
        val basicLocationOptions =
            MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        map.addMarker(basicLocationOptions.position(ub).title("University of Barcelona"))

        // segon exemple loc
        val fav = LatLng(41.380, 2.17)
        val favLocationOptions =
            MarkerOptions()//.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.drawable.favorite)))
        map.addMarker(favLocationOptions.position(fav).title("Favorite spot"))
        map.moveCamera(CameraUpdateFactory.newLatLng(fav))
        */
        //Zoom level 0 corresponds to the fully zoomed-out world view.
        // Most areas support zoom levels up to 20, while more remote areas
        // only support zoom levels up to 13.
        // A zoom level of 12 is a nice in-between value that shows enough
        // detail without getting crazy-close.
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(ub, 12.0f))
        //demanem permis localitzacio a usuari
        setUpMap()
        //mostrem localitzacio usuari
        // habilitem capa mylocation
        map.isMyLocationEnabled = true

        // busquem localitzacio mes recent
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // movem la camara a la localitzacio mes recent
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

        }

        override fun onMarkerClick(p0: Marker?) = false

        //request fine location to user if not already given
        private fun setUpMap() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                return
            }
            map.isMyLocationEnabled = true

            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    placeMarkerOnMap(currentLatLng)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }
            }
        }

        //marcador de posicio de usuari personalitzat
        private fun placeMarkerOnMap(location: LatLng) {
            // crea marcador
            val markerOptions = MarkerOptions().position(location)
            markerOptions.icon(
                BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(resources, R.mipmap.ic_user_location)
                )
            )
            //Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_resource);
            // afegeix al mapa
            map.addMarker(markerOptions)
        }

        // start update location
        private fun startLocationUpdates() {
            //1
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                return
            }
            //2
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null /* Looper */
            )
        }

        // update user location
        private fun createLocationRequest() {
            // 1
            locationRequest = LocationRequest()
            // 2
            locationRequest.interval = 10000
            // 3
            locationRequest.fastestInterval = 5000
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

            // 4
            val client = LocationServices.getSettingsClient(this)
            val task = client.checkLocationSettings(builder.build())

            // 5
            task.addOnSuccessListener {
                locationUpdateState = true
                startLocationUpdates()
            }
            task.addOnFailureListener { e ->
                // 6
                if (e is ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        e.startResolutionForResult(
                            this@MapsActivity,
                            REQUEST_CHECK_SETTINGS
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore
                    }
                }
            }
        }


        // aux not used yet. translates from coordinates to adress
        // for setting new spots/events
        private fun getAddress(latLng: LatLng): String {
            val geocoder = Geocoder(this)
            val addresses: List<Address>?
            val address: Address?
            var addressText = ""

            try {
                addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if (null != addresses && !addresses.isEmpty()) {
                    address = addresses[0]
                    for (i in 0 until address.maxAddressLineIndex) {
                        addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(
                            i
                        )
                    }
                }
            } catch (e: IOException) {
                Log.e("MapsActivity", e.localizedMessage)
            }
            return addressText
        }


        // firebase get data
        fun getAllSpotsOnMap() {
            db.collection("spots").get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val nombre: String? = document.getString("Title")
                        val geopoint: GeoPoint? = document.getGeoPoint("geopoint")
                        val lat: Double = geopoint!!.getLatitude()
                        val lng: Double = geopoint!!.getLongitude()
                        val latLng = LatLng(lat, lng)
                        // primer exemple basic
                        val basicLocationOptions = MarkerOptions().icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)
                        )
                        map.addMarker(basicLocationOptions.position(latLng).title(nombre))
                        map.setOnInfoWindowClickListener(object :
                            GoogleMap.OnInfoWindowClickListener {
                            override fun onInfoWindowClick(marker: Marker) {
                                val intent1 =
                                    Intent(this@MapsActivity, MainActivity::class.java)
                                val title = marker.title
                                intent1.putExtra("spotTitle", title)
                                startActivity(intent1)
                        }
                    })
                }
            }
         }


        }
