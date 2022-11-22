package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.BookSearchDelegate
import com.example.thelibrary.mvp.views.BookSearchView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BookSearchPresenterImpl : BookSearchPresenter, ViewModel() {
    var mView: BookSearchView? = null
    var mLibraryModel: LibraryModel = LibraryModelImpl
    override fun iniView(view: BookSearchView) {
        mView = view
    }

    override fun searchBookByInput(input: String) {
        mLibraryModel.searchGoogleBooks(input).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                mView?.showResultBooks(it)
            },
                {

                })
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapItem(book: BookVO) {
        mLibraryModel.insertBookVO(book)
        mView?.navigateToBookDetail(book.title)
    }
}