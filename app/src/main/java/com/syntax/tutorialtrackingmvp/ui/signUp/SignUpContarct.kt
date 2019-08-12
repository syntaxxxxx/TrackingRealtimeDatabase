package com.syntax.tutorialtrackingmvp.ui.signUp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.syntax.tutorialtrackingmvp.base.BaseView

interface SignUpContarct {

    interface PresenterInterface {
        fun doSignUp(name: String, email: String, password: String, confPassword: String, mAuth: FirebaseAuth?)
    }

    interface ViewInterface : BaseView{
        fun isSuccess(user : FirebaseUser?)
        fun isError(msg: String)
        fun isMinChar()
        fun areNotSame()
        fun isEmptyField()
    }
}