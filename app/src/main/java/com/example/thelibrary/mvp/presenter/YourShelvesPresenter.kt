package com.example.thelibrary.mvp.presenter

import com.example.thelibrary.delegate.ShelvesDelegate
import com.example.thelibrary.mvp.views.HomeView
import com.example.thelibrary.mvp.views.YourShelvesView

interface YourShelvesPresenter:ShelvesDelegate,IBasePresenter {
    fun initHomeView(view: YourShelvesView)
    fun onTapCreateNewLibrary()

}