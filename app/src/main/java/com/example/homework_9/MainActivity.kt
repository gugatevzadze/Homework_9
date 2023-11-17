package com.example.homework_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CategoryAdapter.OnCategoryClickListener {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding

    private val categoryList = listOf(
        Category("All"),
        Category("\uD83C\uDF89 Party"),
        Category("\uD83C\uDFD5 Camping"),
        Category("\uD83D\uDCA5 Category1"),
        Category("\uD83D\uDD25 Category2"),
        Category("\uD83E\uDD8D Category3"),
    )

    private val itemList = listOf(
        Item(1, "model_1", "Belt suit blazer", "$120", "\uD83C\uDF89 Party"),
        Item(2, "model_2", "Belt suit blazer", "$110", "\uD83C\uDFD5 Camping"),
        Item(3, "model_3", "Belt suit blazer", "$90", "\uD83D\uDCA5 Category1"),
        Item(4, "model_4", "Belt suit blazer", "$70", "\uD83D\uDD25 Category2"),
        Item(5, "model_1", "Belt suit blazer", "$125", "\uD83E\uDD8D Category3"),
        Item(6, "model_2", "Belt suit blazer", "$85", "\uD83C\uDF89 Party"),
        Item(7, "model_3", "Belt suit blazer", "$135", "\uD83C\uDFD5 Camping"),
        Item(8, "model_4", "Belt suit blazer", "$115", "\uD83D\uDCA5 Category1"),
        Item(9, "model_1", "Belt suit blazer", "$120", "\uD83D\uDD25 Category2"),
        Item(10, "model_2", "Belt suit blazer", "$120", "\uD83E\uDD8D Category3")
    )

    //list to store the selected categories
    private val selectedCategories: MutableList<Category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //category adapter
        categoryAdapter = CategoryAdapter(categoryList, this)
        binding.categoryView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryView.adapter = categoryAdapter
        //item adapter
        itemAdapter = ItemAdapter()
        binding.itemView.layoutManager = GridLayoutManager(this, 2)
        binding.itemView.adapter = itemAdapter

        //setting up initial state
        updateItemAdapter()
    }

    //overriding the interface
    override fun onCategoryClick(category: Category) {
        if (category.name == "All") {
            //if "all" is clicked, clear selected categories
            selectedCategories.clear()
        } else {
            if (selectedCategories.contains(category)) {
                //if category is already selected, remove it
                selectedCategories.remove(category)
            } else {
                //if category is not selected, add it
                selectedCategories.add(category)
            }
        }
        updateItemAdapter()
    }

    private fun updateItemAdapter() {
        val filteredItems = if (selectedCategories.isEmpty()) {
            //showing all categories together
            itemList
        } else {
            //filter items based on the selected categories
            itemList.filter { selectedCategories.contains(Category(it.category)) }
        }
        itemAdapter.setData(filteredItems)
    }
}