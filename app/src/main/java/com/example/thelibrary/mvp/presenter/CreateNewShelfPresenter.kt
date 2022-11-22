package com.example.thelibrary.mvp.presenter

import com.example.thelibrary.mvp.views.CreateNewShelfView

interface CreateNewShelfPresenter {
    fun iniView(view:CreateNewShelfView)
    fun onTapDoneToCreateShelf(shelfName:String)
}