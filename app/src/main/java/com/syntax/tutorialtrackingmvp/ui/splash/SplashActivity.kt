package com.syntax.tutorialtrackingmvp.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syntax.tutorialtrackingmvp.R

class SplashActivity : AppCompatActivity(), SplashContract.ViewInterface {

    private val presenter by lazy {
        SplashPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
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
