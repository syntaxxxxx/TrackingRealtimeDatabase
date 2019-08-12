package com.syntax.tutorialtrackingmvp.ui.signUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.model.User
import com.syntax.tutorialtrackingmvp.ui.signIn.SignIn
import com.syntax.tutorialtrackingmvp.ui.user.ListUserActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignUp : AppCompatActivity(), SignUpContarct.ViewInterface {

    private val presenter by lazy {
        SignUpPresenter()
    }

    private var database: DatabaseReference? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // inisialisasi database dan create database dengan key user
        database = FirebaseDatabase.getInstance().reference.child("user")

        // inisialisasi auth nya
        mAuth = FirebaseAuth.getInstance()

        presenter
        onAttachView()

        btn_signUp.setOnClickListener {
            presenter.doSignUp(
                edt_username.text.toString(),
                edt_email.text.toString(),
                edt_pass.text.toString(),
                edt_conf_pass.text.toString(), mAuth
            )
        }
        tv_signIn.setOnClickListener { startActivity<SignIn>() }
    }

    override fun isSuccess(user: FirebaseUser?) {

        // save data user ke database base on uuid
        val users = User()
        users.name = edt_username.text.toString().trim()
        users.email = edt_email.text.toString().trim()
        users.password = edt_pass.text.toString().trim()
        users.uuid = user?.uid!!.toString()
        database?.child(user.uid)?.setValue(users)
        startActivity<ListUserActivity>()
    }

    override fun isError(msg: String) {
        toast(msg)
    }

    override fun isMinChar() {
        toast("password min 6 karakter")
    }

    override fun areNotSame() {
        toast("pass dan conf tidak sama")
    }

    override fun isEmptyField() {
        toast("tidak boleh kosong")
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
