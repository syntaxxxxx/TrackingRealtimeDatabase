package com.syntax.tutorialtrackingmvp.ui.user

import com.google.firebase.database.DatabaseReference
import com.syntax.tutorialtrackingmvp.base.BaseView
import com.syntax.tutorialtrackingmvp.model.Users

interface ListUserContract {

    interface PresenterInterface {
        fun doGetUser(database: DatabaseReference?)
    }

    interface ViewInterface : BaseView {
        fun isSuccess(data: List<Users>)
        fun isError(msg : String)
    }
}