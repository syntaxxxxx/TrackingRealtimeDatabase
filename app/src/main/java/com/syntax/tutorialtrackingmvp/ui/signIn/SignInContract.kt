package com.syntax.tutorialtrackingmvp.ui.signIn

import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.base.BaseView

interface SignInContract {

    interface PresenterInterface {
        fun doSignIn(email: String, password: String, mAuth: FirebaseAuth?)
    }

    interface ViewInterface : BaseView {
        fun isSuccess()
        fun isEmptyField()
        fun isError(msg: String)
    }
}