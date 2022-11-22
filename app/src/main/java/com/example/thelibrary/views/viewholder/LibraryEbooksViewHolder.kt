package com.example.thelibrary.views.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import kotlinx.android.synthetic.main.view_holder_library_ebooks.view.*

class LibraryEbooksViewHolder(itemView: View, val mDelegate: LibraryBooksDelegate) :
    RecyclerView.ViewHolder(itemView) {


    fun bindData(book: LibraryBooksVO) {
        itemView.tvLibraryBookTitle.text = book.title
        Glide.with(itemView.context).load(book.bookImage).into(itemView.tvBooksImage)
        itemView.ivLibraryOptions.setOnClickListener {
            mDelegate.onTapOptions(book.title, book.bookImage ?: "", book.author ?: "")
        }
        itemView.setOnClickListener {
            mDelegate.onTapItem(book.title)
        }
    }

    fun bindDataBookVO(book: BookVO){
        itemView.tvLibraryBookTitle.text = book.title
        Glide.with(itemView.context).load(book.bookImage)
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample)
            .into(itemView.tvBooksImage)
        itemView.ivLibraryOptions.setOnClickListener {
            mDelegate.onTapOptions(book.title, book.bookImage ?: "", book.author ?: "")
        }
        itemView.setOnClickListener {
            mDelegate.onTapItem(book.title)
        }
    }
}