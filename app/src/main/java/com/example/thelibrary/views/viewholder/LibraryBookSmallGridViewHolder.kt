package com.example.thelibrary.views.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import kotlinx.android.synthetic.main.view_holder_library_small_grid.view.*
import kotlinx.android.synthetic.main.view_holder_list_layout.view.*

class LibraryBookSmallGridViewHolder(itemView: View, val mDelegate: LibraryBooksDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(book: LibraryBooksVO) {
        itemView.tvLibraryBookTitleSmallGrid.text = book.title
        Glide.with(itemView.context).load(book.bookImage)
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample)
            .into(itemView.ivBooksImageSmallGrid)
        itemView.ivLibraryOptionsSmallGrid.setOnClickListener {
            mDelegate.onTapOptions(book.title, book.bookImage ?: "", book.author ?: "")
        }
        itemView.setOnClickListener {
            mDelegate.onTapItem(book.title)
        }
    }
}