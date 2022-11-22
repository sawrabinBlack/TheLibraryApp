package com.example.thelibrary.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.persistance.entities.ShelvesVO
import com.example.thelibrary.utils.getEbooksDemo
import io.reactivex.rxjava3.core.Observable

class MockLibraryModelImpl : LibraryModel {
    override fun getBookList(onFailure: (String) -> Unit): LiveData<List<ListVO>>? {
        val liveData = MutableLiveData<List<ListVO>>()
        liveData.postValue(getEbooksDemo())
        return liveData
    }

    override fun insertBookList(list: List<BookVO>) {

    }

    override fun insertShelf(shelf: ShelvesVO) {
    }

    override fun createNewShelf(shelfName: String) {

    }

    override fun getShelvesList(): LiveData<List<ShelvesVO>>? {
        val liveData = MutableLiveData<List<ShelvesVO>>()

        return liveData
    }

    override fun getLibraryListBooks(): LiveData<List<LibraryBooksVO>>? {
        val liveData = MutableLiveData<List<LibraryBooksVO>>()
        return liveData
    }

    override fun addBookToLibrary(title: String) {

    }

    override fun getLibraryBooks(title: String): LiveData<LibraryBooksVO?>? {
val liveData = MutableLiveData<LibraryBooksVO>()
        return liveData
    }

    override fun insertShelfList(shelfList: List<ShelvesVO>) {

    }

    override fun getShelfDetail(title: String): LiveData<ShelvesVO?>? {
        val liveData = MutableLiveData<ShelvesVO>()

        return liveData
    }

    override fun deleteBookFromLibrary(title: String) {

    }

    override fun renameShelfName(shelfName: String, updateShelfName: String) {

    }

    override fun deleteShelf(shelfName: String) {

    }

    override fun getBookListMoreBooks(
        list: String,
        onSuccess: (List<ListVO>?) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun insertBookVO(book: BookVO) {

    }

    override fun searchGoogleBooks(q: String): Observable<List<BookVO>> {
        return Observable.just(listOf())
    }
}