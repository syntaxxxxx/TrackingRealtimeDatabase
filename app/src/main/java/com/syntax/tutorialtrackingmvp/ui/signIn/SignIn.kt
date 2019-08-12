package com.syntax.tutorialtrackingmvp.ui.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syntax.tutorialtrackingmvp.R

class SignIn : AppCompatActivity(), SignInContract.ViewInterface {

    private val presenter by lazy {
        SignInPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter
        onAttachView()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettah()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }
}
