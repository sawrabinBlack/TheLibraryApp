package com.example.thelibrary.views.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.BookSearchDelegate
import kotlinx.android.synthetic.main.view_holder_book_search.view.*

class BooksSearchViewHolder(itemView: View, val mDelegate: BookSearchDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(book: BookVO) {
        itemView.tvBooksTitleSearch.text = book.title
        itemView.tvBookAuthorSearch.text = book.author
        Glide.with(itemView.context).load(book.bookImage).error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(itemView.ivBookImageSearch)
        Log.println(Log.INFO, "image", book.bookImage.toString())
        itemView.setOnClickListener {
            mDelegate.onTapItem(book)
        }
    }


}