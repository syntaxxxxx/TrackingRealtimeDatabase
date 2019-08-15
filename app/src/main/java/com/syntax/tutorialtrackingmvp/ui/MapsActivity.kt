package com.syntax.tutorialtrackingmvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.model.Locations
import org.jetbrains.anko.toast

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsContract.ViewInterface {


    private lateinit var mMap: GoogleMap
    private var dr: DatabaseReference? = null

    private val presenter by lazy {
        MapsPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        presenter
        onAttachView()

        dr = FirebaseDatabase.getInstance().getReference().child("locations").child(intent.getStringExtra("id"))
        presenter.doGetLocation(dr)

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
        mMap = googleMap
    }

    override fun isSuccess(latLng: LatLng?) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(latLng!!).title("Marker in My Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }

    override fun isError(msg: String) {
        toast(msg)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettah()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }
}
