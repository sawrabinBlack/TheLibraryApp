package com.example.thelibrary.delegate

import com.example.thelibrary.data.vos.BookVO

interface BookSearchDelegate {
    fun onTapItem(book:BookVO)
}