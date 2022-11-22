package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.BookDetailView
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailPresenterImpl : BookDetailPresenter,ViewModel() {
    var mView: BookDetailView? = null
    var mLibraryModel: LibraryModel = LibraryModelImpl

    override fun onUiReady(owner: LifecycleOwner, title: String) {
        mLibraryModel.getLibraryBooks(title ?: "")?.observe(owner) {
            it?.let {
                mView?.showBookDetail(it)
            }

        }
        mLibraryModel.addBookToLibrary(title)
    }

    override fun iniView(view: BookDetailView) {
mView=view
    }

}