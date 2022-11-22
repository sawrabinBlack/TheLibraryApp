package com.example.thelibrary.mvp.presenter

import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import com.example.thelibrary.delegate.HomeFragmentDelegate
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.mvp.views.HomeView

interface HomePresenter:IBasePresenter,  EbooksDelegate,
    EbooksWIthGenreDelegate {
    fun initHomeView(view: HomeView)
    fun onTapDeleteFromLibrary(title:String)
    fun onTapAddToLibrary(title: String)
}