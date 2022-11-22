package com.example.thelibrary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.views.viewholder.LibraryEbooksViewHolder

class LibraryBooksAdapter(val mDelegate: LibraryBooksDelegate) :
    RecyclerView.Adapter<LibraryEbooksViewHolder>() {
    var mLibraryBookList: List<LibraryBooksVO> = listOf()
    var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryEbooksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_library_ebooks, parent, false)
        return LibraryEbooksViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: LibraryEbooksViewHolder, position: Int) {
        if (mLibraryBookList.isNotEmpty()) {
            holder.bindData(mLibraryBookList[position])
        }

        if (mBookList.isNotEmpty()) {
            holder.bindDataBookVO(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (mLibraryBookList.isEmpty()) {
            mBookList.count()
        } else mLibraryBookList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: List<LibraryBooksVO>) {
        mLibraryBookList = list
        notifyDataSetChanged()

    }

    fun setNewBookData(list: List<BookVO>) {
        mBookList = list
        notifyDataSetChanged()

    }
}