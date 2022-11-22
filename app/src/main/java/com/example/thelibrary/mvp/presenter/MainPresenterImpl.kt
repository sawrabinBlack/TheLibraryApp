package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.HomeView
import com.example.thelibrary.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {
    var mMainView: MainView? = null
    var mHomeView: HomeView? = null
    var mMovieModel: LibraryModel = LibraryModelImpl
    override fun initView(view: MainView) {
        mMainView = view
    }

//    override fun initHomeView(view: HomeView) {
//        mHomeView = view
//    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

//    override fun onTapSearch() {
//    }
//
//    override fun onTapOptionsByViewPod() {
//
//    }
//
//    override fun onTapOption() {
////        Log.println(Log.INFO, "test", mMainView.toString())
//        mMainView?.displayBottomSheet()
//    }
//
//    override fun onTapItem(bookName: String) {
//        mHomeView?.navigateToBookDetailScreen(bookName)
//    }
//
//    override fun onTapTitle(titleName: String) {
//        mHomeView?.navigateToMoreBooksScreen(titleName)
//    }
//
//    override fun onTapSortBy() {
//
//    }
//
//    override fun onTapViewLayout() {
//
//    }
//
//    override fun onTapOptionMenu() {
//
//    }
}