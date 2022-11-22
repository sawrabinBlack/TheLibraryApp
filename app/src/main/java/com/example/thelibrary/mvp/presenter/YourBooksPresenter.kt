package com.example.thelibrary.mvp.presenter

import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.mvp.views.YourBooksView

interface YourBooksPresenter:IBasePresenter,LibraryViewPodDelegate{
fun iniView(view:YourBooksView)

}