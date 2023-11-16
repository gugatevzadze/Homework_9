package com.example.homework_9

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_9.databinding.CategoryBinding

class CategoryAdapter(
    private val categories: List<Category>,
    private val listener: OnCategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var lastSelectedPosition: Int = RecyclerView.NO_POSITION

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }

    inner class CategoryViewHolder(val binding: CategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.categoryName.text = category.name

        holder.itemView.setOnClickListener {
            listener.onCategoryClick(category)
            if (holder.itemView.isSelected) {
                //if selected, unselect and get to original state
                holder.itemView.isSelected = false
                holder.itemView.setBackgroundResource(R.drawable.category_background)
                val hexColor = "#96A7AF"
                val color = Color.parseColor(hexColor)
                holder.binding.categoryName.setTextColor(color)
            } else {
                //if not selected, toggle to the selected state
                holder.itemView.isSelected = true
                holder.itemView.setBackgroundResource(R.drawable.category_background_selected)
                holder.binding.categoryName.setTextColor(Color.WHITE)
            }
        }
    }

    override fun getItemCount(): Int = categories.size
}

