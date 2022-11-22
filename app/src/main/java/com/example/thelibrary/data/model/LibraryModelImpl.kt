package com.example.thelibrary.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.persistance.entities.ShelvesVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object LibraryModelImpl : LibraryModel, BaseModel() {
override fun insertBookList(list:List<BookVO>){
    mLibraryDatabase?.booksDao()?.insertData(list)
}
    override fun getBookList(onFailure: (String) -> Unit): LiveData<List<ListVO>>? {
        mLibraryApi.getBookList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                mLibraryDatabase?.ListDao()?.insertList(it.results?.lists ?: listOf())
                Log.println(Log.INFO, "database", "ok")
                it.results?.lists?.forEach { listVO ->
                    listVO.books?.forEach { book ->
                        book.listName = listVO.listName
                    }

                    mLibraryDatabase?.booksDao()?.insertData(listVO.books ?: listOf())


                }
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mLibraryDatabase?.ListDao()?.getAllBookLists()
    }

    override fun insertShelf(shelf: ShelvesVO) {
        mLibraryDatabase?.shelvesDao()?.createShelf(shelf)
    }

    override fun createNewShelf(shelfName: String) {
        val shelf = ShelvesVO(name = shelfName, books = mutableListOf())
        mLibraryDatabase?.shelvesDao()?.createShelf(shelf)
    }

    override fun getShelvesList(): LiveData<List<ShelvesVO>>? {
        return mLibraryDatabase?.shelvesDao()?.getShelves()
    }

    override fun getLibraryListBooks(): LiveData<List<LibraryBooksVO>>? {
        return mLibraryDatabase?.libraryDao()?.getBooksFromLibrary()
    }

    override fun addBookToLibrary(title: String) {
        val book = mLibraryDatabase?.booksDao()?.getBooksByOneShot(title)
        book?.let {
            val libraryBook = LibraryBooksVO(
                it.author,
                it.bookImage,
                it.bookImageWidth,
                it.bookImageHeight,
                it.bookReviewLink,
                it.contributor,
                it.createdDate,
                it.contributorNote,
                it.description,
                it.publisher,
                it.title,
                it.listName
            )
            mLibraryDatabase?.libraryDao()?.insertData(libraryBook)
        }

    }

    override fun getLibraryBooks(title: String): LiveData<LibraryBooksVO?>? {
        return mLibraryDatabase?.libraryDao()?.getLibraryBookOnShot(title)
    }

    override fun insertShelfList(shelfList: List<ShelvesVO>) {
        mLibraryDatabase?.shelvesDao()?.insertShelfList(shelfList)
    }

    override fun getShelfDetail(title: String): LiveData<ShelvesVO?>? {
        return mLibraryDatabase?.shelvesDao()?.getShelfDetail(title)
    }

    override fun deleteBookFromLibrary(title: String) {
        mLibraryDatabase?.libraryDao()?.deleteBooksFromLibrary(title)
    }

    override fun renameShelfName(shelfName: String, updateShelfName: String) {
        mLibraryDatabase?.shelvesDao()?.renameShelfName(updateShelfName, shelfName)
    }

    override fun deleteShelf(shelfName: String) {
        mLibraryDatabase?.shelvesDao()?.deleteShelf(shelfName)
    }

    override fun getBookListMoreBooks(
        list: String, onSuccess: (List<ListVO>?) -> Unit, onFailure: (String) -> Unit
    ) {

        mLibraryApi.getBookListWithGenre(list = list).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                onSuccess(it.results)
                Log.println(Log.INFO, "msg", it.results.toString())
            }, {
                onFailure(it.localizedMessage ?: "")
                Log.println(Log.INFO, "msg", it.localizedMessage ?: "")
            })

    }

    override fun insertBookVO(book: BookVO) {
        mLibraryDatabase?.booksDao()?.insertOneBook(book)
    }

    override fun searchGoogleBooks(q: String): Observable<List<BookVO>> {
        return mGoogleBookApi.searchGoogleBooks(q).map {
            it.items ?.map {
                itemsVO ->  itemsVO.transformToLibraryVO()
            }?: listOf()
        }.onErrorResumeNext { Observable.just(listOf()) }.subscribeOn(Schedulers.io())
    }
}