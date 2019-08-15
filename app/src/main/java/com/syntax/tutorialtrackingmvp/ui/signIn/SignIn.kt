package com.syntax.tutorialtrackingmvp.ui.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.ui.signUp.SignUp
import com.syntax.tutorialtrackingmvp.ui.user.ListUserActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignIn : AppCompatActivity(), SignInContract.ViewInterface {

    private val presenter by lazy {
        SignInPresenter()
    }

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        presenter
        onAttachView()

        btn_signIn.setOnClickListener {
            presenter.doSignIn(
                edt_email_login.text.toString().trim(),
                edt_pass_login.text.toString().trim(), mAuth
            )
        }

        tv_signUp.setOnClickListener { startActivity<SignUp>() }
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

    override fun isEmptyField() {
        toast("tidak boleh kosong")
    }

    override fun isError(msg: String) {
        toast(msg)
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
