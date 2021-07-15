package ru.kavunov.mtsproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kavunov.mtsproject.DTC.Categories
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.databinding.CategoryItemBinding

class CategoryAdapter(ListMain: List<Categories>):RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    var categtList = ListMain

    class CategoryHolder(item: View):RecyclerView.ViewHolder(item) {
        val building = CategoryItemBinding.bind(item)
        fun bind(category: Categories){
            building.idCateg.text = category.category
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categtList[position])
    }

    override fun getItemCount(): Int {
        return categtList.size
    }
}