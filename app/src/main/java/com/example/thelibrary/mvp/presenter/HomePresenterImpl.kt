package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.HomeView

class HomePresenterImpl : HomePresenter, ViewModel() {
    var mHomeView: HomeView? = null
    var mLibraryModel: LibraryModel = LibraryModelImpl
    override fun initHomeView(view: HomeView) {
        mHomeView = view
    }

    override fun onTapDeleteFromLibrary(title: String) {
        mLibraryModel.deleteBookFromLibrary(title)
    }

    override fun onTapAddToLibrary(title: String) {
        mLibraryModel.addBookToLibrary(title)
        mHomeView?.navigateToAddToShelvesScreen(title)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getBookList { }?.observe(owner) {
            mHomeView?.showBookList(it)
        }

        mLibraryModel.getLibraryListBooks()?.observe(owner) {
            mHomeView?.showCarouselView(it.reversed())
        }
    }


    override fun onTapOption(bookName: String, author: String, bookImage: String) {
        mHomeView?.showOptionBottomSheet(bookName, author, bookImage)
    }

    override fun onTapItem(bookName: String) {
        mHomeView?.navigateToBookDetailScreen(bookName)
    }

    override fun onTapTitle(titleName: String) {
        mHomeView?.navigateToMoreBooksScreen(titleName)
    }


}