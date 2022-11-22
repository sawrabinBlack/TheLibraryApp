package com.example.thelibrary.utils

import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.data.vos.ListVO

fun getEbooksDemo(): List<ListVO> {

    return listOf(
        ListVO(
            listName = "HardCover Fiction",
            listId = null,
            books = listOf(
                BookVO(author = "Michael Connelly", title = "DESERT STAR"),
                BookVO(author = "John Grisham", title = "The boys From biloxi"),
                BookVO(author = "Michael Connelly", title = "DESERT STAR")
            ),
            bookDetail = null,
            displayName = null,
            listNameEncoded = null
        ),
        ListVO(
            listName = "HardCover Fiction",
            listId = null,
            books = listOf(
                BookVO(author = "Michael Connelly", title = "DESERT STAR"),
                BookVO(author = "John Grisham", title = "The boys From biloxi"),
                BookVO(author = "Michael Connelly", title = "DESERT STAR")
            ),
            bookDetail = null,
            displayName = null,
            listNameEncoded = null
        ),

        ListVO(
            listName = "HardCover Fiction",
            listId = null,
            books = listOf(
                BookVO(author = "Michael Connelly", title = "DESERT STAR"),
                BookVO(author = "John Grisham", title = "The boys From biloxi"),
                BookVO(author = "Michael Connelly", title = "DESERT STAR")
            ),
            bookDetail = null,
            displayName = null,
            listNameEncoded = null
        ),

    )
}