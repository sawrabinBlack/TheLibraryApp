package com.example.thelibrary.delegate

interface LibraryBooksDelegate {
    fun onTapOptions(title:String,bookImage:String,author:String)
    fun onTapItem(title: String)
}