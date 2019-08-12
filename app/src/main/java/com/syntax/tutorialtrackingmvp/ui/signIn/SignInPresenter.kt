package com.syntax.tutorialtrackingmvp.ui.signIn

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.base.BasePresenter

class SignInPresenter(var _view: SignInContract.ViewInterface? = null) : BasePresenter<SignInContract.ViewInterface>
    , SignInContract.PresenterInterface {

    override fun onAttach(view: SignInContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }

    override fun doSignIn(email: String, password: String, mAuth: FirebaseAuth?) {

        if (email.isEmpty() || password.isEmpty()) {
            _view?.isEmptyField()

        } else {
            mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {
                if (it.isSuccessful && it.isComplete) {
                    Log.d("TAG", "signInWithEmail:success")
                    _view?.isSuccess()
                } else {
                    Log.w("TAG", "signInWithEmail:failure : ", it.exception)
                    _view?.isError("signInWithEmail:failure :" + it.exception.toString())
                }
            }
        }
    }
}