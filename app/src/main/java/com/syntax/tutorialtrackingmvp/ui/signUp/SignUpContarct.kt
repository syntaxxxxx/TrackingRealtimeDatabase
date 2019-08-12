package com.syntax.tutorialtrackingmvp.ui.signUp

import com.syntax.tutorialtrackingmvp.base.BaseView

interface SignUpContarct {

    interface PresenterInterface {
        fun doSignUp(name: String, email: String, password: String, isSuccess :(Boolean) -> Unit)
    }

    interface ViewIntervace : BaseView{
        fun isSuccess()
        fun isError()
    }
}