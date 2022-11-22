package com.example.thelibrary.mvp.views

import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.persistance.entities.LibraryBooksVO

interface HomeView : BaseView {

    fun showBookList(BookListsWithGenre: List<ListVO>)
    fun showCarouselView(BookList: List<LibraryBooksVO>)
    fun navigateToBookDetailScreen(title:String)
    fun navigateToMoreBooksScreen(listTitle:String)
    fun navigateToAddToShelvesScreen(title: String)
    fun showOptionBottomSheet(bookName: String, author: String, bookImage: String)
}