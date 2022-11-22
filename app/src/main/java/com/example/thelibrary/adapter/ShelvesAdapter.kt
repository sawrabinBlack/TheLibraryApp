package com.example.thelibrary.adapter

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.delegate.ShelvesDelegate
import com.example.thelibrary.persistance.entities.ShelvesVO
import com.example.thelibrary.views.viewholder.ShelfViewHolder

class ShelvesAdapter(val mDelegate: ShelvesDelegate) : RecyclerView.Adapter<ShelfViewHolder>() {
    var mShelvesList: List<ShelvesVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_shelf, parent, false)
        return ShelfViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
        if (mShelvesList.isNotEmpty()) {
            holder.bindData(mShelvesList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelvesList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: List<ShelvesVO>) {
        mShelvesList = list
        notifyDataSetChanged()
    }
}