package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner: LifecycleOwner)
}