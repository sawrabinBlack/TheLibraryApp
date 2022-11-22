package com.example.thelibrary.views.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thelibrary.R
import com.example.thelibrary.delegate.AddToShelfDelegate
import com.example.thelibrary.persistance.entities.ShelvesVO
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButton.OnCheckedChangeListener
import com.google.android.material.chip.ChipGroup
import com.google.android.material.chip.ChipGroup.OnCheckedStateChangeListener
import kotlinx.android.synthetic.main.view_holder_add_to_shelves.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class AddToShelvesViewHolder(itemView: View, val mDelegae: AddToShelfDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(shelf: ShelvesVO) {
        itemView.tvShelfNamesAdd.text = shelf.name
        itemView.tvNoOfBooksAdd.text = "${shelf.books?.count()} books"
        itemView.cbAddToShelves.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                mDelegae.onSelectedLibrary(shelf.name)
                Log.println(Log.INFO, "check", "ok")
            } else {
                mDelegae.onUnSelectedLibrary(shelf.name)
                Log.println(Log.INFO, "check", "uncheck")
            }
        }
        Glide.with(itemView.context).load(shelf.books?.getOrNull(0)?.bookImage ?: "")
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(itemView.ivBookImageFrontAddToShelves)
        Glide.with(itemView.context).load(shelf.books?.getOrNull(1)?.bookImage ?: "")
            .error(R.drawable.book_sample)
            .placeholder(R.drawable.book_sample).into(itemView.ivBookImageAddToShelves)


    }
}