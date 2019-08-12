package com.syntax.tutorialtrackingmvp.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.ui.signIn.SignIn
import com.syntax.tutorialtrackingmvp.ui.user.ListUserActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity(), SplashContract.ViewInterface {

    private val presenter by lazy {
        SplashPresenter()
    }

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()

        presenter
        onAttachView()
        presenter.delay(3000, mAuth)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettah()
    }

    override fun isSuccess() {
        startActivity<ListUserActivity>()
        finish()
    }

    override fun isError() {
        startActivity<SignIn>()
        finish()
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
