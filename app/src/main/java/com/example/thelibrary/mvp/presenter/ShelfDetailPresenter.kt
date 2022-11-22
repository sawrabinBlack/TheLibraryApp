package com.example.thelibrary.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.mvp.views.ShelfDetailView

interface ShelfDetailPresenter:IBasePresenter,LibraryViewPodDelegate {
fun iniView(view:ShelfDetailView)
fun onTapMenu()
fun onTapDeleteShelf(shelfName: String)
fun renameShelf(updateShelfName:String,oldShelfName:String)
fun onUiReady(shelfName :String,owner: LifecycleOwner)

}