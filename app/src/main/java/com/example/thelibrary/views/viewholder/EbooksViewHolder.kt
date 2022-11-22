package com.example.thelibrary.views.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.EbooksDelegate
import kotlinx.android.synthetic.main.view_holder_ebooks.view.*
import java.util.*

class EbooksViewHolder(itemView: View, private val mDelegate: EbooksDelegate) :
    RecyclerView.ViewHolder(itemView) {

    init {

    }


    fun bindData(books: BookVO) {
        Log.println(Log.INFO, "book", books.toString())
        itemView.setOnClickListener {
            mDelegate.onTapItem(books.title)
        }
        itemView.tvBookTitle.text = books.title.lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        Glide.with(itemView.context).load(books.bookImage).error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(itemView.ivBooksImage)
        itemView.ivOptions.setOnClickListener {
            mDelegate.onTapOption(books.title, books.author ?: "", books.bookImage ?: "")
        }
    }
}