package com.example.thelibrary.mvp.views

import com.example.thelibrary.persistance.entities.LibraryBooksVO

interface BookDetailView {
 fun showBookDetail(book:LibraryBooksVO)
}