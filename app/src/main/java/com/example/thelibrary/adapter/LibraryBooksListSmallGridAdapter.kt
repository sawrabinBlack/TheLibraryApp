package com.example.thelibrary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.LibraryBooksDelegate
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.views.viewholder.LibraryBookSmallGridViewHolder
import com.example.thelibrary.views.viewholder.LibraryBooksListLayoutViewHolder

class LibraryBooksListSmallGridAdapter(val mDelegate: LibraryBooksDelegate): RecyclerView.Adapter<LibraryBookSmallGridViewHolder>() {
    var mLibraryBookList: List<LibraryBooksVO> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibraryBookSmallGridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_library_small_grid, parent, false)
        return LibraryBookSmallGridViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: LibraryBookSmallGridViewHolder, position: Int) {
        if (mLibraryBookList.isNotEmpty()) {
            holder.bindData(mLibraryBookList[position])
        }
    }

    override fun getItemCount(): Int {
       return mLibraryBookList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: List<LibraryBooksVO>) {
        mLibraryBookList = list
        notifyDataSetChanged()

    }

}