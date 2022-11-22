package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.mvp.views.MoreBooksView

interface MoreBooksPresenter : IBasePresenter,LibraryBooksDelegate {
    fun iniView(view: MoreBooksView)
    fun onUiReady(owner: LifecycleOwner, category: String)
    fun onTapDeleteFromLibrary(title:String)
    fun onTapAddToLibrary(title: String)
}