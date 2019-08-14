package com.syntax.tutorialtrackingmvp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.model.User
import kotlinx.android.synthetic.main.activity_list_user.*
import org.jetbrains.anko.toast

class ListUserActivity : AppCompatActivity(), ListUserContract.ViewInterface {

    private var database: DatabaseReference? = null

    private val presenter by lazy {
        ListUserPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        database = FirebaseDatabase.getInstance().reference.child("user")

        presenter
        onAttachView()

        presenter.doGetUser(database)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettah()
    }

    override fun isSuccess(data: List<User>) {
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
