package com.example.thelibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.delegate.AddToShelfDelegate
import com.example.thelibrary.persistance.entities.ShelvesVO
import com.example.thelibrary.views.viewholder.AddToShelvesViewHolder

class AddToShelvesAdapter(val mDelegate:AddToShelfDelegate) : RecyclerView.Adapter<AddToShelvesViewHolder>() {
    var mShelvesList: List<ShelvesVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelvesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_add_to_shelves, parent, false)
        return AddToShelvesViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: AddToShelvesViewHolder, position: Int) {
        if (mShelvesList.isNotEmpty()) {
            holder.bindData(mShelvesList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelvesList.count()
    }

    fun setNewData(list: List<ShelvesVO>) {
        mShelvesList = list
        notifyDataSetChanged()
    }
}