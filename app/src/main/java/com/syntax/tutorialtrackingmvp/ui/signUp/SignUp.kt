package com.syntax.tutorialtrackingmvp.ui.signUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syntax.tutorialtrackingmvp.R

class SignUp : AppCompatActivity(), SignUpContarct.ViewIntervace {

    private val presenter by lazy {
        SignUpPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        presenter
        onAttachView()
    }

    override fun isSuccess() {

    }

    override fun isError() {
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
