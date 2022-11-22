package com.example.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibrary.R
import com.example.thelibrary.adapter.BookSearchAdapter
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.mvp.presenter.BookSearchPresenter
import com.example.thelibrary.mvp.presenter.BookSearchPresenterImpl
import com.example.thelibrary.mvp.views.BookSearchView
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_book_search.*
import java.util.concurrent.TimeUnit

class BookSearchActivity : AppCompatActivity(), BookSearchView {
    var mLibraryModel: LibraryModel = LibraryModelImpl
    lateinit var mPresenter: BookSearchPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookSearchActivity::class.java)
        }
    }

    lateinit var mAdapter: BookSearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        setUpPresenter()
        mAdapter = BookSearchAdapter(mPresenter)
        rvSearchMovies.adapter = mAdapter
        rvSearchMovies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        etSearch.textChanges().debounce(500L, TimeUnit.MILLISECONDS).subscribe {
            mPresenter.searchBookByInput(it.toString())
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookSearchPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    override fun showResultBooks(bookList: List<BookVO>) {
        mAdapter.setNewData(bookList)
    }

    override fun navigateToBookDetail(title: String) {
        startActivity(BookDetailActivity.newIntent(this, title))
    }
}