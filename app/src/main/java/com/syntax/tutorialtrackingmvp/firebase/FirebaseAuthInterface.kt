package com.syntax.tutorialtrackingmvp.firebase

interface FirebaseAuthInterface {

    fun doSignUp(name: String, email: String, password: String, isSuccess: (Boolean) -> Unit)
    fun doSignIn(email: String, password: String, isSuccess: (Boolean) -> Unit)
}