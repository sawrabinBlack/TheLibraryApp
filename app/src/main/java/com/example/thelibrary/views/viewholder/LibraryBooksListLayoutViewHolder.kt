package com.example.thelibrary.views.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import kotlinx.android.synthetic.main.view_holder_list_layout.view.*

class LibraryBooksListLayoutViewHolder(itemView: View, val mDelegate: LibraryBooksDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(book: LibraryBooksVO) {
        itemView.tvBookAuthorListLayout.text = book.author
        itemView.tvBooksTitleListLayout.text = book.title
        Glide.with(itemView.context).load(book.bookImage)
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample)
            .into(itemView.ivBookImageList)
        itemView.ivOptionsListLayout.setOnClickListener {
            mDelegate.onTapOptions(book.title,  book.bookImage ?: "",book.author ?: "")
        }

        itemView.ivBookImageList.setOnClickListener{
            mDelegate.onTapItem(book.title)
        }

    }
}