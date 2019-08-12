package com.syntax.tutorialtrackingmvp.base

interface BasePresenter<T : BaseView> {

    fun onAttach(view: T)
    fun onDettah()
}