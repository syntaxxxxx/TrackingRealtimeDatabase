package com.syntax.tutorialtrackingmvp.ui.user

import android.util.Log
import com.google.firebase.database.*
import com.syntax.tutorialtrackingmvp.base.BasePresenter
import com.syntax.tutorialtrackingmvp.model.User

class ListUserPresenter(var _view: ListUserContract.ViewInterface? = null) :
    BasePresenter<ListUserContract.ViewInterface>, ListUserContract.PresenterInterface {

    var data: MutableList<User> = mutableListOf()

    override fun onAttach(view: ListUserContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }

    override fun doGetUser(database: DatabaseReference?) {

        // Read from the database
        database?.addValueEventListener(object : ValueEventListener {

            // This method is called once with the initial value and again
            // whenever data at this location is updated
            override fun onDataChange(p0: DataSnapshot) {
                data.clear()
                for (dataSnapshot in p0.children) {
                    val users = User()
                    val getDataUser = dataSnapshot.getValue(User::class.java)

                    // Set data to model user
                    users.uuid = getDataUser?.uuid!!
                    users.name = getDataUser.name
                    users.email = getDataUser.email

                    data.addAll(listOf(users))
                }
                Log.d("TAG", "Read values is success")
                _view?.isSuccess(data)
            }

            override fun onCancelled(p0: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", p0.toException())
                _view?.isError(p0.toException().toString())
            }
        })
    }
}