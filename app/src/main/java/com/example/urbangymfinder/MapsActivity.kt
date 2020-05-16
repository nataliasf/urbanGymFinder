package com.example.urbangymfinder

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private lateinit var map: GoogleMap
    private lateinit var lastLocation: Location

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
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

        // Add a marker in Barcelona and move the camera
        //41°23'11.4"N 2°09'49.3"E
        // primer exemple basic
        val ub = LatLng(41.387, 2.164)
        val basicLocationOptions =  MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        map.addMarker(basicLocationOptions.position(ub).title("University of Barcelona"))

        // segon exemple loc
        val fav = LatLng(41.380, 2.17)
        val favLocationOptions = MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.drawable.favorite)))
        map.addMarker(favLocationOptions.position(fav).title("Favorite spot"))
        map.moveCamera(CameraUpdateFactory.newLatLng(fav))

        //Zoom level 0 corresponds to the fully zoomed-out world view.
        // Most areas support zoom levels up to 20, while more remote areas
        // only support zoom levels up to 13.
        // A zoom level of 12 is a nice in-between value that shows enough
        // detail without getting crazy-close.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ub, 12.0f))
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
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
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
            BitmapFactory.decodeResource(resources, R.mipmap.ic_user_location)))
        //Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_resource);
        // afegeix al mapa
        map.addMarker(markerOptions)
    }
}
