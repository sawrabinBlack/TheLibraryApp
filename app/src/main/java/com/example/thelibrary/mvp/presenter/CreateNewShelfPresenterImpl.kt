package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.ViewModel
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.views.CreateNewShelfView

class CreateNewShelfPresenterImpl : CreateNewShelfPresenter,ViewModel(){
    var mView: CreateNewShelfView? = null
    var mLibraryModel: LibraryModel = LibraryModelImpl
    override fun iniView(view: CreateNewShelfView) {
        mView = view
    }

    override fun onTapDoneToCreateShelf(shelfName: String) {
        mLibraryModel.createNewShelf(shelfName)
    }
}