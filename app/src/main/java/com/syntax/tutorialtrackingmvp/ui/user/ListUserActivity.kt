package com.syntax.tutorialtrackingmvp.ui.user

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.model.Users
import com.syntax.tutorialtrackingmvp.service.TrackingService
import kotlinx.android.synthetic.main.activity_list_user.*
import org.jetbrains.anko.toast

class ListUserActivity : AppCompatActivity(), ListUserContract.ViewInterface {

    private val REQUEST_PERMISSIONS = 1
    private var database: DatabaseReference? = null

    private val presenter by lazy {
        ListUserPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        presenter
        onAttachView()

        database = FirebaseDatabase.getInstance().reference.child("user")

        //Check whether this app has access to the location permission//
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        // If the location permission has been granted, then start the TrackerService
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService()
        } else {

            //If the app doesn’t currently have access to the user’s location, then request access//

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS
            )
        }
        presenter.doGetUser(database)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        // If the permission has been granted
        if (requestCode == REQUEST_PERMISSIONS && grantResults.size == 1
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            // then start the GPS tracking service
            startTrackerService()
        } else {
            // If the user denies the permission request, then display a toast with some more information
            toast("Please enable location services to allow GPS tracking")
        }
    }

    private fun startTrackerService() {
        startService(Intent(this, TrackingService::class.java))
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettah()
    }

    override fun isSuccess(data: List<Users>) {
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = ListUserAdapter(data)
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun isError(msg: String) {
        toast(msg)
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
