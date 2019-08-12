package com.syntax.tutorialtrackingmvp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syntax.tutorialtrackingmvp.R

class ListUserActivity : AppCompatActivity(), ListUserContract.ViewInterface {

    private val presenter by lazy {
        ListUserPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
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
