package com.example.thelibrary.adapter

import alirezat775.lib.carouselview.CarouselAdapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import kotlinx.android.synthetic.main.view_holder_carousel.view.*
import kotlinx.android.synthetic.main.view_holder_ebooks.view.*

class BooksCarouselAdapter(val mDelegate: EbooksDelegate) : CarouselAdapter() {
    private var mCarouselViewHolder: CarouselAdapter.CarouselViewHolder? = null
    private var mCardList: List<LibraryBooksVO> = listOf()

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        mCarouselViewHolder = holder
        if (mCardList.isNotEmpty()) {
            (mCarouselViewHolder as LibraryBooksViewHolder).bindData(mCardList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_carousel, parent, false)
        return LibraryBooksViewHolder(view, mDelegate)
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }

    fun setNewData(list: List<LibraryBooksVO>) {
        mCardList = list
        notifyDataSetChanged()
    }

    inner class LibraryBooksViewHolder(itemView: View, val mDelegate: EbooksDelegate) :
        CarouselViewHolder(itemView) {

        fun bindData(books: LibraryBooksVO) {
            Glide.with(itemView.context).load(books.bookImage)
                .error(R.drawable.book_sample)
                .placeholder(R.drawable.book_sample).into(itemView.ivBookImageCarousel)
            itemView.ivOptionsCarousel.setOnClickListener {
                mDelegate.onTapOption(books.title, books.author ?: "", books.bookImage ?: "")
            }

            itemView.setOnClickListener {
                mDelegate.onTapItem(books.title)
            }
        }
    }
}