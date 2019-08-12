package com.syntax.tutorialtrackingmvp.ui.user

import com.syntax.tutorialtrackingmvp.base.BasePresenter

class ListUserPresenter(var _view: ListUserContract.ViewInterface? = null) :
    BasePresenter<ListUserContract.ViewInterface> {

    override fun onAttach(view: ListUserContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }
}