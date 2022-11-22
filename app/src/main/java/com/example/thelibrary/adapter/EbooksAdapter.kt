package com.example.thelibrary.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import com.example.thelibrary.views.viewholder.EbooksViewHolder

class EbooksAdapter(val mDelegate: EbooksDelegate) : RecyclerView.Adapter<EbooksViewHolder>() {
    var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbooksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ebooks, parent, false)
        return EbooksViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: EbooksViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bindData(mBookList[position])
        }

    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: List<BookVO>) {
        Log.println(Log.INFO,"booklistss",list.toString())
        mBookList = list
        notifyDataSetChanged()
    }
}