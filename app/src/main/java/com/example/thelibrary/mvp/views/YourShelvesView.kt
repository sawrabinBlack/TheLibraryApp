package com.example.thelibrary.mvp.views

import com.example.thelibrary.persistance.entities.ShelvesVO

interface YourShelvesView {
    fun showShelves(shelfList:List<ShelvesVO>)
    fun navigateToAddNewShelfScreen()
    fun navigateToShelfDetail(shelfName:String)
}