package com.syntax.tutorialtrackingmvp.firebase

class FirebaseAuthManager : FirebaseAuthInterface {
    override fun doSignUp(name: String, email: String, password: String, isSuccess: (Boolean) -> Unit) {

    }

    override fun doSignIn(email: String, password: String, isSuccess: (Boolean) -> Unit) {
    }
}