package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.ViewModel
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import com.example.thelibrary.delegate.HomeFragmentDelegate
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.mvp.views.HomeView
import com.example.thelibrary.mvp.views.MainView

interface MainPresenter:IBasePresenter {
    fun initView(view: MainView)

}