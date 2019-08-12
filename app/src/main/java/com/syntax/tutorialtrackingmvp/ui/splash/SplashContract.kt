package com.syntax.tutorialtrackingmvp.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.base.BaseView

interface SplashContract {

    interface PresenterInterface {
        fun delay(timer : Long, mAuth: FirebaseAuth?)
    }
    interface ViewInterface : BaseView {
        fun isSuccess()
        fun isError()
    }
}