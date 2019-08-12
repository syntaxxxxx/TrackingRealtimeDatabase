package com.syntax.tutorialtrackingmvp.ui.splash

import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.base.BasePresenter

class SplashPresenter(var _view: SplashContract.ViewInterface? = null) :
    BasePresenter<SplashContract.ViewInterface>, SplashContract.PresenterInterface {

    override fun onAttach(view: SplashContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }

    override fun delay(timer: Long, mAuth: FirebaseAuth?) {
        Handler().postDelayed(Runnable {

            // check jika user belum pernah login
            if (mAuth?.currentUser != null) {
                _view?.isSuccess()
            } else {
                _view?.isError()
            }
        }, timer)
    }
}