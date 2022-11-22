package com.example.thelibrary.views.viewpod

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.activities.AddToShelvesActivity
import com.example.thelibrary.activities.BookDetailActivity
import com.example.thelibrary.adapter.CategoryAdapter
import com.example.thelibrary.adapter.LibraryBooksAdapter
import com.example.thelibrary.adapter.LibraryBooksListLayoutAdapter
import com.example.thelibrary.adapter.LibraryBooksListSmallGridAdapter
import com.example.thelibrary.data.vos.CategoryVO
import com.example.thelibrary.delegate.CategoryListDelegate
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jakewharton.rxbinding4.internal.checkMainThread
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.bottom_sheet_books_options.*
import kotlinx.android.synthetic.main.bottom_sheet_layout_choose.*
import kotlinx.android.synthetic.main.bottom_sheet_sort_by.*
import kotlinx.android.synthetic.main.view_holder_ebooks.view.*
import kotlinx.android.synthetic.main.view_pod_library_with_filter_and_layout.view.*


class LibraryWithFilterAndLayoutViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs), LibraryBooksDelegate, CategoryListDelegate {
    lateinit var mAdapter: LibraryBooksAdapter
    lateinit var mListLayoutAdapter: LibraryBooksListLayoutAdapter
    lateinit var mCategoryAdapter: CategoryAdapter
    lateinit var mSmallGridAdapter: LibraryBooksListSmallGridAdapter
    lateinit var bottomSheetOptions: BottomSheetDialog
    lateinit var bottomSheetLayout: BottomSheetDialog
    lateinit var bottomSheet: BottomSheetDialog
    var mDelegate: LibraryViewPodDelegate? = null
    var mList: List<LibraryBooksVO> = listOf()
    var mSortList: List<LibraryBooksVO> = listOf()
    var mCategoryList: MutableList<String> = mutableListOf()
    var mCategoryVOList = mutableListOf<CategoryVO>()
    var mCategorySortList = mutableListOf<CategoryVO>()


    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpCategoryListRecyclerView()
        setUpInitialView()
        setUpListRecyclerView()
        setUpLargeGridRecyclerView()
        setUpSmallGridRecyclerView()
        setUpdialog()
        btnClearFilter.setOnClickListener {
            setUpData(mList)
            btnClearFilter.visibility=View.GONE
        }

    }

    private fun setUpSmallGridRecyclerView() {
        mSmallGridAdapter = LibraryBooksListSmallGridAdapter(this)
        rvLibraryEbooksThreeColumn.adapter = mSmallGridAdapter
        rvLibraryEbooksThreeColumn.layoutManager = GridLayoutManager(context, 3)

    }

    private fun setUpdialog() {
        bottomSheet = BottomSheetDialog(context)
        bottomSheetLayout = BottomSheetDialog(context)
        bottomSheetOptions = BottomSheetDialog(context)
        bottomSheetOptions.setContentView(R.layout.bottom_sheet_books_options)
        bottomSheetLayout.setContentView(R.layout.bottom_sheet_layout_choose)
        bottomSheet.setContentView(R.layout.bottom_sheet_sort_by)
        bottomSheet.rgSortBy.setOnCheckedChangeListener { _, checkId ->
            when (checkId) {
                R.id.rbRecent -> {
                    setDataForRecyclerViews(mSortList)
                }
                R.id.rbAuthor -> {
                    val mListByAuthor = mSortList.sortedBy { it.author }
                    setDataForRecyclerViews(mListByAuthor)
                }
                R.id.rbTitle -> {
                    val mListByTitle = mSortList.sortedBy { it.title }
                    setDataForRecyclerViews(mListByTitle)
                }
            }
            bottomSheet.dismiss()
        }
        bottomSheetLayout.rgLayout.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.rbList -> {
                    setUpSortView("List")
                }
                R.id.rbSmallGrid -> {
                    setUpSortView("SmallGrid")
                }
                R.id.rbLargeGrid -> {
                    setUpSortView("LargeGrid")
                }
            }
            bottomSheetLayout.dismiss()
            Log.println(Log.INFO, "radio", checkedId.toString())
        }
        tvSortBy.setOnClickListener {
            bottomSheet.show()
        }

        tvViewType.setOnClickListener {
            bottomSheetLayout.show()
        }

    }

    private fun setUpCategoryListRecyclerView() {
        mCategoryAdapter = CategoryAdapter(this)
        chipGroup.adapter = mCategoryAdapter
        chipGroup.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }


    private fun setUpLargeGridRecyclerView() {
        mAdapter = LibraryBooksAdapter(this)
        rvLibraryEbooksTwoColumn.adapter = mAdapter
        rvLibraryEbooksTwoColumn.layoutManager = GridLayoutManager(context, 2)

    }

    private fun setUpListRecyclerView() {
        mListLayoutAdapter = LibraryBooksListLayoutAdapter(this)
        rvLibraryEbooks.adapter = mListLayoutAdapter
        rvLibraryEbooks.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun setUpViewPod(delegate: LibraryViewPodDelegate) {
        this.mDelegate = delegate


    }

    fun setUpData(list: List<LibraryBooksVO>) {
        mCategoryVOList = mutableListOf()
        mCategoryList = mutableListOf()
        mCategoryVOList = mutableListOf()
        setDataForRecyclerViews(list)
        mList = list
        mSortList = list
        list.forEach {
            mCategoryList.add(it.listName ?: "")
        }
        val categoryList = mCategoryList.distinct()
        categoryList.forEach {
            mCategoryVOList.add(CategoryVO(name = it))
            mCategorySortList.add(CategoryVO(name = it))
        }
        mCategoryAdapter.setUpData(mCategoryVOList)
    }

    private fun setDataForRecyclerViews(list: List<LibraryBooksVO>) {
        mAdapter.setNewData(list)
        mListLayoutAdapter.setNewData(list)
        mSmallGridAdapter.setNewData(list)
    }


    private fun setUpSortView(layout: String) {
        when (layout) {
            VIEW_TYPE_LARGE_GRID -> {
                showLargeGridView()
            }
            VIEW_TYPE_SMALL_GRID -> {
                showSmallGridView()
            }
            VIEW_TYPE_LIST -> {
                showListView()
            }
        }

    }

    private fun showListView() {
        rvLibraryEbooksThreeColumn.visibility = GONE
        rvLibraryEbooksTwoColumn.visibility = GONE
        rvLibraryEbooks.visibility = VISIBLE
    }

    private fun showSmallGridView() {
        rvLibraryEbooksThreeColumn.visibility = VISIBLE
        rvLibraryEbooksTwoColumn.visibility = GONE
        rvLibraryEbooks.visibility = GONE
    }

    private fun showLargeGridView() {
        rvLibraryEbooksThreeColumn.visibility = GONE
        rvLibraryEbooksTwoColumn.visibility = VISIBLE
        rvLibraryEbooks.visibility = GONE
    }

    private fun setUpInitialView() {
        rvLibraryEbooksThreeColumn.visibility = GONE
        rvLibraryEbooks.visibility = VISIBLE
        rvLibraryEbooksTwoColumn.visibility = GONE
    }

    override fun onTapOptions(title: String, bookImage: String, author: String) {
        bottomSheetOptions.tvAuthorBottomSheet.text = author
        bottomSheetOptions.tvBookTitleBottomSheet.text = title
        Glide.with(context).load(bookImage)  .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(bottomSheetOptions.ivBookImageBtSheet)
        bottomSheetOptions.tvAddToShelves.setOnClickListener {
            bottomSheetOptions.dismiss()
            mDelegate?.onTapAddToShelves(title)
        }
        bottomSheetOptions.tvDelete.setOnClickListener {

            mDelegate?.onTapDelete(title)
            bottomSheetOptions.dismiss()
        }
        bottomSheetOptions.show()
    }

    override fun onTapItem(title: String) {
        mDelegate?.onTapBook(title)
    }

    override fun onTapItem(name: String, isChecked: Boolean) {

        if (isChecked) {
            btnClearFilter.visibility = View.GONE
            mCategoryAdapter.setUpData(mCategoryVOList)
            setDataForRecyclerViews(mList)
            mSortList = mList
        } else {
            btnClearFilter.visibility = View.VISIBLE
            mSortList = mList.filter { it.listName == name }
            setDataForRecyclerViews(mSortList)
            mCategorySortList.forEach {
                if (it.name == name) {
                    it.isChecked = it.isChecked != true
                } else it.isChecked = false
            }

            mCategorySortList.sortByDescending {
                it.isChecked
            }

            mCategoryAdapter.setUpData(mCategorySortList.distinctBy { it.name })
        }

    }


}

const val VIEW_TYPE_LIST = "List"
const val VIEW_TYPE_SMALL_GRID = "SmallGrid"
const val VIEW_TYPE_LARGE_GRID = "LargeGrid"