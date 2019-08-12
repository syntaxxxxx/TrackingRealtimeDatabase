package com.syntax.tutorialtrackingmvp.ui.splash

import com.syntax.tutorialtrackingmvp.base.BasePresenter

class SplashPresenter(var _view: SplashContract.ViewInterface? = null) :
    BasePresenter<SplashContract.ViewInterface> {

    override fun onAttach(view: SplashContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }
}