package com.example.thelibrary.views.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.adapter.EbooksAdapter
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.delegate.EbooksDelegate
import com.example.thelibrary.delegate.EbooksWIthGenreDelegate
import kotlinx.android.synthetic.main.view_holder_ebooks.view.*
import kotlinx.android.synthetic.main.view_holder_ebooks_with_genre.view.*

class EbooksWithGenreViewHolder(
    itemView: View,
    val mEbooksDelegate: EbooksDelegate,
    val mEbooksWithGenreDelegate: EbooksWIthGenreDelegate
) : RecyclerView.ViewHolder(itemView) {

    init {

    }

    fun bindData(list: ListVO) {
        itemView.tvEbooksGenre.text = list.displayName
        val mEbooksAdapter = EbooksAdapter(mEbooksDelegate)

        itemView.rvEbooks.adapter = mEbooksAdapter
        itemView.rvEbooks.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        mEbooksAdapter.setNewData(list.books ?: listOf())
        itemView.tvEbooksGenre.setOnClickListener {
            mEbooksWithGenreDelegate.onTapTitle(list.listNameEncoded?:"")

        }
    }

}