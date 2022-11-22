package com.example.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.adapter.EbooksAdapter
import com.example.thelibrary.adapter.LibraryBooksAdapter
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.mvp.presenter.MoreBooksPresenter
import com.example.thelibrary.mvp.presenter.MoreBooksPresenterImpl
import com.example.thelibrary.mvp.views.MoreBooksView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_more_ebooks.*
import kotlinx.android.synthetic.main.bottom_sheet_books_options.*

class MoreEbooksActivity : AppCompatActivity(), MoreBooksView {
    lateinit var mPresenter: MoreBooksPresenter
    var mLibraryModel: LibraryModel = LibraryModelImpl
    lateinit var mEbooksAdapter: LibraryBooksAdapter
    lateinit var mDialog: BottomSheetDialog

    var mListName: String? = null

    companion object {
        const val IE_MORE_EBOOKS = "IE_MORE_EBOOKS"
        fun newIntent(context: Context, data: String): Intent {
            val intent = Intent(context, MoreEbooksActivity::class.java)
            intent.putExtra(IE_MORE_EBOOKS, data)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_ebooks)
        setUpPresenter()
        mListName = intent.getStringExtra(IE_MORE_EBOOKS)
        mListName?.let {
            mPresenter.onUiReady(this, it)
        }
        mDialog = BottomSheetDialog(this)
        mDialog.setContentView(R.layout.bottom_sheet_books_options)
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        mEbooksAdapter = LibraryBooksAdapter(mPresenter)
        rvMoreEbooks.adapter = mEbooksAdapter
        rvMoreEbooks.layoutManager =
            GridLayoutManager(this, 2)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MoreBooksPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    override fun showBookList(bookList: List<BookVO>, displayName: String) {
        tvBookGenreMoreBooks.text = displayName
        mEbooksAdapter.setNewBookData(bookList)
    }

    override fun navigateToBookDetail(bookName: String) {
        startActivity(BookDetailActivity.newIntent(this, bookName))
    }

    override fun showOptionBottomSheet(bookName: String, author: String, bookImage: String) {
        mDialog.tvBookTitleBottomSheet.text = bookName
        mDialog.tvAuthorBottomSheet.text = author
        Glide.with(this).load(bookImage)
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(mDialog.ivBookImageBtSheet)
        mDialog.show()
        mDialog.tvAddToShelves.setOnClickListener {

            mPresenter.onTapAddToLibrary(bookName)
        }
        mDialog.tvDelete.setOnClickListener {
            mPresenter.onTapDeleteFromLibrary(bookName)
            mDialog.dismiss()
        }
    }

    override fun navigateToAddToShelves(title: String) {
        startActivity(AddToShelvesActivity.newIntent(this, title))
    }

}