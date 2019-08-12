package com.syntax.tutorialtrackingmvp.ui.signUp

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.base.BasePresenter

class SignUpPresenter(
    var _view: SignUpContarct.ViewInterface? = null
) : BasePresenter<SignUpContarct.ViewInterface>,
    SignUpContarct.PresenterInterface {

    override fun onAttach(view: SignUpContarct.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }

    override fun doSignUp(name: String, email: String, password: String, confPassword: String, mAuth: FirebaseAuth?) {

        // check jika inputan user kosong
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _view?.isEmptyField()

            // check password minimal 3 karakter
        } else if (password.length < 6) {
            _view?.isMinChar()

            // check jika password dan confirm password tidak sama
        } else if (password != confPassword) {
            _view?.areNotSame()

        } else {

            // sign up user
            mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {
                if (it.isSuccessful && it.isComplete) {
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = mAuth.currentUser

                    // simpan user dan sampaikan ke view
                    _view?.isSuccess(user)
                } else {
                    Log.w("TAG", "createUserWithEmail:failure", it.getException())
                    _view?.isError("createUserWithEmail:failure" + it.exception.toString())
                }
            }
        }
    }
}