package com.example.thelibrary.mvp.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.ShelfDetailView
import com.example.thelibrary.persistance.entities.ShelvesVO

class ShelfDetailPresenterImpl : ShelfDetailPresenter, ViewModel() {
    var mView: ShelfDetailView? = null
    var mLibraryModel: LibraryModel = LibraryModelImpl
    var mShelf: ShelvesVO? = null

    override fun iniView(view: ShelfDetailView) {
        mView = view
    }

    override fun onTapMenu() {
        mView?.showBottomSheet()
    }

    override fun onTapDeleteShelf(shelfName: String) {
        mLibraryModel.deleteShelf(shelfName)
    }

    override fun renameShelf(updateShelfName: String, oldShelfName: String) {
        mLibraryModel.renameShelfName(
            shelfName = oldShelfName,
            updateShelfName = updateShelfName
        )
    }

    override fun onUiReady(shelfName: String, owner: LifecycleOwner) {
        mLibraryModel.getShelfDetail(shelfName)?.observe(owner)
        {
            it?.let {
                mView?.showShelfDetail(it)
                mShelf=it
            }
        }
    }


    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBook(title: String) {
        mView?.navigateToBookDetail(title)
    }

    override fun onTapAddToShelves(title: String) {
        mView?.navigateToAddToShelves(title)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTapDelete(title: String) {
        mShelf?.books?.removeIf {
            it.title == title
        }

        mShelf?.let {
            mLibraryModel.insertShelf(it)
        }

    }
}