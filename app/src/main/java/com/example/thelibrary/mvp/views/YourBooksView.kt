package com.example.thelibrary.mvp.views

import com.example.thelibrary.persistance.entities.LibraryBooksVO

interface YourBooksView:BaseView {
    fun showLibraryBookList(libraryBooks:List<LibraryBooksVO>)
    fun navigateToBookDetail(title:String)
    fun navigateToAddToShelves(title: String)
}