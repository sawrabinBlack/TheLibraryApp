package com.example.thelibrary.data.model

import androidx.lifecycle.LiveData
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.persistance.entities.ShelvesVO
import io.reactivex.rxjava3.core.Observable

interface LibraryModel {

    fun getBookList(
        onFailure: (String) -> Unit
    ): LiveData<List<ListVO>>?

    fun insertBookList(list:List<BookVO>)
    fun insertShelf(shelf: ShelvesVO)
    fun createNewShelf(shelfName: String)
    fun getShelvesList(): LiveData<List<ShelvesVO>>?
    fun getLibraryListBooks(): LiveData<List<LibraryBooksVO>>?
    fun addBookToLibrary(title: String)
    fun getLibraryBooks(title: String): LiveData<LibraryBooksVO?>?
    fun insertShelfList(shelfList: List<ShelvesVO>)
    fun getShelfDetail(title: String): LiveData<ShelvesVO?>?
    fun deleteBookFromLibrary(title: String)
    fun renameShelfName(shelfName: String, updateShelfName: String)
    fun deleteShelf(shelfName: String)
    fun getBookListMoreBooks(
        list: String,
        onSuccess: (List<ListVO>?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun insertBookVO(book: BookVO)

    fun searchGoogleBooks(q: String): Observable<List<BookVO>>
}