package com.syntax.tutorialtrackingmvp.ui

import com.syntax.tutorialtrackingmvp.base.BasePresenter

class MapsPresenter(var _view: MapsContract.ViewInterface? = null) : BasePresenter<MapsContract.ViewInterface> {

    override fun onAttach(view: MapsContract.ViewInterface) {
        _view = view
    }

    override fun onDettah() {
        _view = null
    }
}