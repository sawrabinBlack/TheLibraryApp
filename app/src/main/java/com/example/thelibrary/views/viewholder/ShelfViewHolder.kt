package com.example.thelibrary.views.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.delegate.ShelvesDelegate
import com.example.thelibrary.persistance.entities.ShelvesVO
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class ShelfViewHolder(itemView: View,val mDelegate:ShelvesDelegate) : RecyclerView.ViewHolder(itemView) {


    fun bindData(shelf: ShelvesVO) {
        itemView.tvLibraryName.text = shelf.name
        itemView.tvNoOfBooksLibrary.text = "${shelf.books?.size ?: 0} book"
        itemView.setOnClickListener{
            mDelegate.onTapItem(shelf.name)
        }
        Glide.with(itemView.context).load(shelf.books?.getOrNull(0)?.bookImage?:"").into(itemView.ivBookImageFrontShelf)
        Glide.with(itemView.context).load(shelf.books?.getOrNull(1)?.bookImage?:"").into(itemView.ivBookImageBack)
    }
}