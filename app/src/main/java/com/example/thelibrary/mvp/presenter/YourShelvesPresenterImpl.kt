package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.YourShelvesView

class YourShelvesPresenterImpl : YourShelvesPresenter, ViewModel() {
    var mLibraryModel: LibraryModel = LibraryModelImpl
    var mView: YourShelvesView? = null
    override fun initHomeView(view: YourShelvesView) {
        mView = view
    }

    override fun onTapCreateNewLibrary() {
        mView?.navigateToAddNewShelfScreen()
    }

    override fun onTapItem(shelfName: String) {
        mView?.navigateToShelfDetail(shelfName)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getShelvesList()?.observe(owner) {
            mView?.showShelves(it)
        }
    }
}