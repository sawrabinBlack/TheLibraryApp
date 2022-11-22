package com.example.thelibrary.delegate

interface EbooksDelegate {
    fun onTapOption(bookName: String,author : String,bookImage:String)
    fun onTapItem(bookName:String)
}