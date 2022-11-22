package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.YourBooksView

class YourBooksPresenterImpl : YourBooksPresenter, ViewModel() {
    var mLibraryModel: LibraryModel = LibraryModelImpl
    var mView: YourBooksView? = null
    override fun iniView(view: YourBooksView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getLibraryListBooks()?.observe(owner) {
            mView?.showLibraryBookList(it)
        }
    }


    override fun onTapBook(title: String) {
        mView?.navigateToBookDetail(title)
    }

    override fun onTapAddToShelves(title: String) {
        mView?.navigateToAddToShelves(title)
    }

    override fun onTapDelete(title: String) {
        mLibraryModel.deleteBookFromLibrary(title)
    }
}