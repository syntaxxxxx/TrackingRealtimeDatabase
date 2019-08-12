package com.syntax.tutorialtrackingmvp.ui.signUp

import com.syntax.tutorialtrackingmvp.base.BasePresenter

class SignUpPresenter(var _view: SignUpContarct.ViewIntervace? = null) : BasePresenter<SignUpContarct.ViewIntervace> {

    override fun onAttach(view: SignUpContarct.ViewIntervace) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }
}