package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.thelibrary.mvp.views.BookDetailView

interface BookDetailPresenter {
    fun onUiReady(owner: LifecycleOwner,title:String)
    fun iniView(view:BookDetailView)
}