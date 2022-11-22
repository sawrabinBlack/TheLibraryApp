package com.example.thelibrary.mvp.views

import com.example.thelibrary.persistance.entities.ShelvesVO

interface AddToShelvesView {
    fun showShelvesList(shelvesList: List<ShelvesVO>)
    fun navigateToPreviousScreen()
}