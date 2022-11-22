package com.example.thelibrary.delegate

interface LibraryViewPodDelegate {
    fun onTapBook(title: String)
    fun onTapAddToShelves(title: String)
    fun onTapDelete(title: String)
}