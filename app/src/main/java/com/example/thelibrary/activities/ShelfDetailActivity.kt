package com.example.thelibrary.activities

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.thelibrary.R
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.presenter.ShelfDetailPresenter
import com.example.thelibrary.mvp.presenter.ShelfDetailPresenterImpl
import com.example.thelibrary.mvp.views.ShelfDetailView
import com.example.thelibrary.persistance.entities.ShelvesVO
import com.example.thelibrary.views.viewpod.LibraryWithFilterAndLayoutViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_create_new_self.*
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.bottom_sheet_shelf_action.*

class ShelfDetailActivity : AppCompatActivity(), ShelfDetailView {
    lateinit var mShelveViewPod: LibraryWithFilterAndLayoutViewPod
    lateinit var mShelfName: String
    lateinit var mPresenter: ShelfDetailPresenter

    companion object {
        const val IE_SHELF_NAME = "SHELF_NAME"
        fun newIntent(context: Context, data: String): Intent {
            val intent = Intent(context, ShelfDetailActivity::class.java)
            intent.putExtra(IE_SHELF_NAME, data)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_detail)
        setUpPresenter()
        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(R.layout.bottom_sheet_shelf_action)

        val mData = intent.getStringExtra(IE_SHELF_NAME)

        mShelveViewPod = vpShelfDetail as LibraryWithFilterAndLayoutViewPod
        mShelveViewPod.setUpViewPod(mPresenter)
        mData?.let {
            mPresenter.onUiReady(it,this)
            bottomSheet.tvShelfNameSBtSheet.text = mData
        }
        etShelfNamesDetail.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_DONE) {
                    showShelfName()
                    tvShelfNamesDetail.text = p0?.text
                    mShelfName = tvShelfNamesDetail.text.toString()
                    mPresenter.renameShelf(mShelfName, mData ?: "")

                }
                return false
            }
        })
        ivShelfAction.setOnClickListener {
            bottomSheet.show()
        }

        btnBackShelfDetail.setOnClickListener {
            super.onBackPressed()
        }
        bottomSheet.tvRenameShelf.setOnClickListener {
            showEditShelfName()
            etShelfNamesDetail.requestFocus()
            bottomSheet.dismiss()
        }
        bottomSheet.tvDelete.setOnClickListener {
            mPresenter.onTapDeleteShelf(mShelfName)
            finish()
            bottomSheet.dismiss()

        }



    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    private fun setUpLibraryData(shelf: ShelvesVO) {
        tvShelfNamesDetail.text = shelf.name
//        etShelfNamesDetail.text = SpannableStringBuilder.valueOf(shelf.name)
        mShelfName = shelf.name
        showShelfName()
        mShelveViewPod.setUpData(shelf.books ?: listOf())
        tvNoOfBooksDetail.text = "${shelf.books?.count()} books"

    }

    private fun showShelfName() {
        tvShelfNamesDetail.visibility = View.VISIBLE
        etShelfNamesDetail.visibility = View.INVISIBLE
    }

    private fun showEditShelfName() {
        tvShelfNamesDetail.visibility = View.INVISIBLE
        etShelfNamesDetail.visibility = View.VISIBLE
    }

    override fun showShelfDetail(shelf: ShelvesVO) {
        setUpLibraryData(shelf)
    }

    override fun showBottomSheet() {

    }

    override fun navigateToBookDetail(bookName: String) {
        startActivity(BookDetailActivity.newIntent(this, bookName))
    }

    override fun navigateToAddToShelves(bookName: String) {
        startActivity(AddToShelvesActivity.newIntent(this, bookName))
    }
}