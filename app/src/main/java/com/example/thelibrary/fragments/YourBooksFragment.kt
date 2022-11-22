package com.example.thelibrary.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import com.example.thelibrary.R
import com.example.thelibrary.activities.AddToShelvesActivity
import com.example.thelibrary.activities.BookDetailActivity
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.mvp.presenter.LibraryPresenter
import com.example.thelibrary.mvp.presenter.YourBooksPresenter
import com.example.thelibrary.mvp.presenter.YourBooksPresenterImpl
import com.example.thelibrary.mvp.views.YourBooksView
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.views.viewpod.LibraryWithFilterAndLayoutViewPod
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_books_options.*
import kotlinx.android.synthetic.main.bottom_sheet_layout_choose.*
import kotlinx.android.synthetic.main.bottom_sheet_sort_by.*
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_your_books.*


class YourBooksFragment() : Fragment(), YourBooksView {
    var mLibraryModel: LibraryModel = LibraryModelImpl
    lateinit var mPresenter: YourBooksPresenter
    lateinit var mContext: Context
    lateinit var mLibraryViewPod: LibraryWithFilterAndLayoutViewPod
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_books, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        mLibraryViewPod = vpYourBooks as LibraryWithFilterAndLayoutViewPod
        mLibraryViewPod.setUpViewPod(mPresenter)
        mPresenter.onUiReady(this)


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[YourBooksPresenterImpl::class.java]
        mPresenter.iniView(this)
    }


    override fun showLibraryBookList(libraryBooks: List<LibraryBooksVO>) {
        mLibraryViewPod.setUpData(libraryBooks)
    }

    override fun navigateToBookDetail(title: String) {
        startActivity(BookDetailActivity.newIntent(mContext, title))
    }

    override fun navigateToAddToShelves(title: String) {
        startActivity(AddToShelvesActivity.newIntent(mContext, title))
    }
}

