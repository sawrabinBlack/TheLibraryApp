package com.example.thelibrary.mvp.views

import com.example.thelibrary.data.vos.BookVO

interface MoreBooksView {
    fun showBookList(bookList: List<BookVO>, displayName: String)
    fun navigateToBookDetail(bookName: String)
    fun showOptionBottomSheet(bookName: String, author: String, bookImage: String)
    fun navigateToAddToShelves(title: String)
}