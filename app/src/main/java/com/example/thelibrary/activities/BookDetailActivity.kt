package com.example.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.presenter.BookDetailPresenter
import com.example.thelibrary.mvp.presenter.BookDetailPresenterImpl
import com.example.thelibrary.mvp.views.BookDetailView
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity(), BookDetailView {
    var mLibraryModel: LibraryModel = LibraryModelImpl
    lateinit var mPresenter: BookDetailPresenter

    companion object {
        const val IE_BOOK_DETAIL = "IE_BOOK_DETAIL"
        fun newIntent(context: Context, data: String): Intent {
            var intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(IE_BOOK_DETAIL, data)
            return intent
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setUpPresenter()
        val data = intent.getStringExtra(IE_BOOK_DETAIL)
        mPresenter.onUiReady(this, data ?: "")
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    override fun showBookDetail(book: LibraryBooksVO) {
        Glide.with(this).load(book.bookImage ?: "")
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(ivBookImageDetail)
        tvBookTitleDetail.text = book.title ?: ""
        tvAuthorDetail.text = book.author ?: ""
        tvBookDetailAbout.text = book.description ?: ""
        tvPublished.text = book.publisher
    }
}