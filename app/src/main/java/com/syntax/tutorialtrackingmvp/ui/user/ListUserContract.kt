package com.syntax.tutorialtrackingmvp.ui.user

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.syntax.tutorialtrackingmvp.base.BaseView
import com.syntax.tutorialtrackingmvp.model.User

interface ListUserContract {

    interface PresenterInterface {
        fun doGetUser(database: DatabaseReference?)
    }

    interface ViewInterface : BaseView {
        fun isSuccess(data: List<User>)
        fun isError(msg : String)
    }
}