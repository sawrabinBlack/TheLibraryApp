package com.example.thelibrary.delegate

interface AddToShelfDelegate {
    fun onSelectedLibrary(shelfName:String)
    fun onUnSelectedLibrary(shelfName: String)
}