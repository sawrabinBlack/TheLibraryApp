package com.example.thelibrary.mvp.presenter

import com.example.thelibrary.delegate.BookSearchDelegate
import com.example.thelibrary.mvp.views.BookSearchView

interface BookSearchPresenter:IBasePresenter,BookSearchDelegate {
    fun iniView(view:BookSearchView)
    fun searchBookByInput(input:String)
}