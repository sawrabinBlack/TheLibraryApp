package com.example.thelibrary.views.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibrary.R
import com.example.thelibrary.data.vos.CategoryVO
import com.example.thelibrary.delegate.CategoryListDelegate
import kotlinx.android.synthetic.main.view_holder_category.view.*

class CategoryViewHolder(itemView: View, val mDelegate: CategoryListDelegate) :
    RecyclerView.ViewHolder(itemView) {
    fun bindData(category: CategoryVO) {
        itemView.setOnClickListener {
            mDelegate.onTapItem(category.name,category.isChecked)
        }
        itemView.tvCategory.text = category.name
        if(category.isChecked)
        {
            itemView.tvCategory.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
            itemView.tvCategory.setBackgroundResource(R.drawable.background_category_selected)
        }
        else{
            itemView.tvCategory.setTextColor(ContextCompat.getColor(itemView.context,R.color.gray))
            itemView.tvCategory.setBackgroundResource(R.drawable.background_category)
        }
        itemView.tvCategory.setOnClickListener {

            mDelegate.onTapItem(category.name,category.isChecked)


        }
    }

}