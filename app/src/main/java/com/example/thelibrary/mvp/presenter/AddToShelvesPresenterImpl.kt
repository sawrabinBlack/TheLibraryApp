package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.AddToShelvesView
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.persistance.entities.ShelvesVO

class AddToShelvesPresenterImpl : AddToShelvesPresenter, ViewModel() {
    var mLibraryModel: LibraryModel = LibraryModelImpl
    var mView: AddToShelvesView? = null
    var mList: List<ShelvesVO> = listOf()
    var mBook: LibraryBooksVO? = null
    override fun iniView(view: AddToShelvesView) {
        mView = view
    }

    override fun onSelectShelves() {
        mLibraryModel.insertShelfList(mList)
    }

    override fun onUiReady(owner: LifecycleOwner, bookName: String) {
        mLibraryModel.getShelvesList()?.observe(owner) {
            mView?.showShelvesList(it)
            mList = it
        }

        mLibraryModel.getLibraryBooks(bookName)?.observe(owner) {
            mBook = it
        }
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }


    override fun onSelectedLibrary(shelfName: String) {
        mList.forEach { shelf ->
            if (shelf.name == shelfName) {
                mBook?.let {

                    if(shelf.books?.contains(it) == false)
                    {
                        shelf.books?.add(it)
                    }

                }

            }
        }
    }

    override fun onUnSelectedLibrary(shelfName: String) {
        mList.forEach { shelf ->
            if (shelf.name == shelfName) {
                mBook?.let {
                    shelf.books?.remove(it)
                }
            }
        }
    }
}