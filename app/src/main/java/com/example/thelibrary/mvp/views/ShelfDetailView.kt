package com.example.thelibrary.mvp.views

import com.example.thelibrary.persistance.entities.ShelvesVO

interface ShelfDetailView {
    fun showShelfDetail(shelf:ShelvesVO)
    fun showBottomSheet()
    fun navigateToBookDetail(bookName:String)
    fun navigateToAddToShelves(bookName:String)

}