package com.example.thelibrary.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.activities.AddToShelvesActivity
import com.example.thelibrary.activities.BookDetailActivity
import com.example.thelibrary.activities.MoreEbooksActivity
import com.example.thelibrary.adapter.BooksCarouselAdapter
import com.example.thelibrary.adapter.EbooksAdapter
import com.example.thelibrary.adapter.EbooksWithGenreAdapter
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import com.example.thelibrary.delegate.HomeFragmentDelegate
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.example.thelibrary.mvp.presenter.HomePresenter
import com.example.thelibrary.mvp.presenter.HomePresenterImpl
import com.example.thelibrary.mvp.presenter.MainPresenter
import com.example.thelibrary.mvp.presenter.MainPresenterImpl
import com.example.thelibrary.mvp.views.HomeView
import com.example.thelibrary.mvp.views.MainView
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_books_options.*
import kotlinx.android.synthetic.main.bottom_sheet_books_options.tvDelete
import kotlinx.android.synthetic.main.bottom_sheet_shelf_action.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {
    private lateinit var mPresenter: HomePresenter
    lateinit var mEbooksAdapter: EbooksWithGenreAdapter
    lateinit var mCreditCardAdapter: BooksCarouselAdapter
    lateinit var mCarousel: Carousel
    lateinit var mApp: AppCompatActivity
    lateinit var mDialog: BottomSheetDialog
    private val tabItemList = listOf("Ebooks", "Audiobooks")
    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mApp = context as AppCompatActivity


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpTabLayout()
        setUpRecyclerView()
        SetUpCarouselView()
        mDialog = BottomSheetDialog(mContext)
        mDialog.setContentView(R.layout.bottom_sheet_books_options)
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[HomePresenterImpl::class.java]
        mPresenter.initHomeView(this)

    }

    private fun setUpTabLayout() {
        tabItemList.forEach {
            tabBooksAndAudio.newTab().apply {
                text = it
                tabBooksAndAudio.addTab(this)
            }
        }
    }

    private fun SetUpCarouselView() {
        mCreditCardAdapter = BooksCarouselAdapter(mPresenter)
        mCarousel = Carousel(mApp, carouselView, mCreditCardAdapter)
        mCarousel.setOrientation(CarouselView.HORIZONTAL, false)
        mCarousel.scaleView(true)
        mCarousel.scrollSpeed(0f)
    }

    private fun setUpRecyclerView() {
        mEbooksAdapter = EbooksWithGenreAdapter(mPresenter, mPresenter)
        rvEbooksWithGenre.adapter = mEbooksAdapter
        rvEbooksWithGenre.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun showBookList(BookListsWithGenre: List<ListVO>) {
        mEbooksAdapter.setNewData(BookListsWithGenre)
    }

    override fun showCarouselView(BookList: List<LibraryBooksVO>) {
        mCreditCardAdapter.setNewData(BookList)
    }

    override fun navigateToBookDetailScreen(title: String) {
        startActivity(BookDetailActivity.newIntent(mContext, title))
    }

    override fun navigateToMoreBooksScreen(listTitle: String) {

        startActivity(MoreEbooksActivity.newIntent(mContext,listTitle))
    }

    override fun navigateToAddToShelvesScreen(title: String) {
        mDialog.dismiss()
        startActivity(AddToShelvesActivity.newIntent(mContext, title))
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
}