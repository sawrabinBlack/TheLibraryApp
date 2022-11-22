package com.example.thelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.BookSearchDelegate
import com.example.thelibrary.views.viewholder.BooksSearchViewHolder

class BookSearchAdapter (val mDelegate: BookSearchDelegate): RecyclerView.Adapter<BooksSearchViewHolder>() {
    var mBookList: List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_book_search, parent, false)
        return BooksSearchViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BooksSearchViewHolder, position: Int) {
if(mBookList.isNotEmpty()){
    holder.bindData(mBookList[position])
}
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }


    fun setNewData(bookList: List<BookVO>) {
        mBookList = bookList
        notifyDataSetChanged()
    }
}