package com.syntax.tutorialtrackingmvp.ui

import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.syntax.tutorialtrackingmvp.base.BasePresenter
import com.syntax.tutorialtrackingmvp.model.Locations

class MapsPresenter(var _view: MapsContract.ViewInterface? = null) : BasePresenter<MapsContract.ViewInterface>,
    MapsContract.PresenterInterface {

    override fun onAttach(view: MapsContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }

    override fun doGetLocation(dr: DatabaseReference?) {
        dr?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                // get location user
                val getDataLocation = p0.getValue(Locations::class.java)

                // lat & long
                val lat = getDataLocation?.latitude
                val lon = getDataLocation?.longitude
                val latLng = LatLng(lat!!, lon!!)
                Log.d("TAG", "Read values is success : ${latLng}")

                // passing data lat & long
                _view?.isSuccess(latLng)
            }

            override fun onCancelled(p0: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value : ${p0.toException()}")
                _view?.isError("Failed to read value : ${p0.toException()}")
            }
        })
    }
}