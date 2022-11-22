package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.thelibrary.delegate.AddToShelfDelegate
import com.example.thelibrary.mvp.views.AddToShelvesView
import com.example.thelibrary.persistance.entities.ShelvesVO

interface AddToShelvesPresenter:IBasePresenter,AddToShelfDelegate {

    fun iniView(view:AddToShelvesView)
    fun onSelectShelves()
    fun onUiReady(owner: LifecycleOwner,bookName:String)
}