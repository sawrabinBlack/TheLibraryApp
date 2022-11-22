package com.example.thelibrary.mvp.views

import com.example.thelibrary.data.vos.BookVO

interface BookSearchView:MainView {
    fun showResultBooks(bookList:List<BookVO>)
    fun navigateToBookDetail(title: String)
}