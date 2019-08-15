package com.syntax.tutorialtrackingmvp.ui

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.syntax.tutorialtrackingmvp.base.BaseView

interface MapsContract {

    interface PresenterInterface {
        fun doGetLocation(dr: DatabaseReference?)
    }

    interface ViewInterface : BaseView {
        fun isSuccess(latLng: LatLng?)
        fun isError(msg: String)
    }
}