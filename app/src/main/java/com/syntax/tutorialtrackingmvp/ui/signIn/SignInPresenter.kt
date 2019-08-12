package com.syntax.tutorialtrackingmvp.ui.signIn

import com.syntax.tutorialtrackingmvp.base.BasePresenter

class SignInPresenter(var _view: SignInContract.ViewInterface? = null) : BasePresenter<SignInContract.ViewInterface> {

    override fun onAttach(view: SignInContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }
}