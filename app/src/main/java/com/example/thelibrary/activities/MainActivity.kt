package com.example.thelibrary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thelibrary.R
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import com.example.thelibrary.delegate.HomeFragmentDelegate
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.fragments.HomeFragment
import com.example.thelibrary.fragments.LibraryFragment
import com.example.thelibrary.fragments.YourBooksFragment
import com.example.thelibrary.mvp.presenter.MainPresenter
import com.example.thelibrary.mvp.presenter.MainPresenterImpl
import com.example.thelibrary.mvp.views.MainView
import com.example.thelibrary.views.viewpod.LibraryWithFilterAndLayoutViewPod
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_books_options.*
import kotlinx.android.synthetic.main.bottom_sheet_layout_choose.*
import kotlinx.android.synthetic.main.bottom_sheet_sort_by.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_your_books.*

class MainActivity : AppCompatActivity(),MainView {
    private lateinit var mPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(HomeFragment())
        setUpPresenter()
        rlSearch.setOnClickListener {
            startActivity(BookSearchActivity.newIntent(this))
        }
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> switchFragment(HomeFragment())
                R.id.action_library -> switchFragment(LibraryFragment())

            }
            true
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]

    }


    private fun switchFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()

    }

//    override fun onTapSearch() {
//
//    }
//
//    override fun onTapTitle() {
//        startActivity(MoreEbooksActivity.newIntent(this))
//    }
//
//    override fun onTapOption() {
//
//
//
//    }
//
//    override fun onTapItem(bookName: String) {
//        startActivity(BookDetailActivity.newIntent(this,bookName))
//    }
//
//    override fun onTapSortBy() {
//        YourBooksFragment().sortBy("Hi")
//        val sheet = BottomSheetBehavior.from(bottomSheetSortOption)
//        sheet.state = BottomSheetBehavior.STATE_EXPANDED
//    }
//
//    override fun onTapViewLayout() {
//        val sheet = BottomSheetBehavior.from(bottomSheetLayout)
//        sheet.state = BottomSheetBehavior.STATE_EXPANDED
//    }
//
//    override fun onTapOptionMenu() {
//
//    }

//    override fun displayBottomSheet() {
//        Log.println(Log.INFO,"sheet","ok")
////        sheet.state = BottomSheetBehavior.STATE_EXPANDED
//        val sheet = BottomSheetBehavior.from(bottomSheetSortOption)
//       sheet.state = BottomSheetBehavior.STATE_EXPANDED
//    }
}