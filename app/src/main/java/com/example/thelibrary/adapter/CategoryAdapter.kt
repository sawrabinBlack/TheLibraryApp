package com.example.thelibrary.adapter

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.CategoryVO
import com.example.thelibrary.delegate.CategoryListDelegate
import com.example.thelibrary.views.viewholder.CategoryViewHolder

class CategoryAdapter (val mDelegate:CategoryListDelegate): RecyclerView.Adapter<CategoryViewHolder>() {
    var mString: List<CategoryVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_category, parent, false)
        return CategoryViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (mString.isNotEmpty()) {
            holder.bindData(mString[position])
        }
    }

    override fun getItemCount(): Int {
        return mString.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpData(categoryNameList: List<CategoryVO>) {
        mString = categoryNameList.distinct()
        notifyDataSetChanged()
    }
}