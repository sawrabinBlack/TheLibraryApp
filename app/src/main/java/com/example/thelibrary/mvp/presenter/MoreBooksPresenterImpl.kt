package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.mvp.views.MoreBooksView

class MoreBooksPresenterImpl : MoreBooksPresenter, ViewModel() {
    var mView: MoreBooksView? = null
    var mDisplayName: String = ""
    var mLibraryModel: LibraryModel = LibraryModelImpl
    var mBooksList: MutableList<BookVO> = mutableListOf()
    override fun iniView(view: MoreBooksView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner, category: String) {
        mLibraryModel.getBookListMoreBooks(category,
            onSuccess = {
                it?.let { listVOlist ->
                    listVOlist.forEach {
                        it.bookDetail?.firstOrNull()?.let { book ->
                            mBooksList.add(book)
                            mDisplayName = it.displayName ?: ""
                        }
                    }
                }
                mView?.showBookList(mBooksList, mDisplayName)
                mLibraryModel.insertBookList(mBooksList)
            }, onFailure = {

            })
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapDeleteFromLibrary(title: String) {
        mLibraryModel?.deleteBookFromLibrary(title)
    }

    override fun onTapAddToLibrary(title: String) {
        mLibraryModel.addBookToLibrary(title)
        mView?.navigateToAddToShelves(title)
    }

    override fun onTapOptions(title: String, bookImage: String, author: String) {
        mView?.showOptionBottomSheet(title, author = author, bookImage = bookImage)
    }


    override fun onTapItem(title: String) {
        mView?.navigateToBookDetail(title)
    }
}