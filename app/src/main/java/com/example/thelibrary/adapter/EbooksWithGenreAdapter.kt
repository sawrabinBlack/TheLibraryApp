package com.example.thelibrary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import com.example.thelibrary.views.viewholder.EbooksWithGenreViewHolder

class EbooksWithGenreAdapter(
    val mEbooksDelegate: EbooksDelegate,
    val mEbooksWithGenre: EbooksWIthGenreDelegate
) : RecyclerView.Adapter<EbooksWithGenreViewHolder>() {
    var mList: List<ListVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbooksWithGenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_ebooks_with_genre, parent, false)
        return EbooksWithGenreViewHolder(view, mEbooksDelegate, mEbooksWithGenre)
    }

    override fun onBindViewHolder(holder: EbooksWithGenreViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.bindData(mList[position])
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: List<ListVO>) {
        mList = list
        notifyDataSetChanged()
    }
}