package ru.kavunov.mtsproject.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.databinding.CategoryItemBinding
import java.util.ArrayList

class CategoryAdapter():RecyclerView.Adapter<CategoryHolder>() {
    var categtList: MutableList<Categorie> = ArrayList()

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
    fun initData(categ: List<Categorie>?) {
        if (categ!=null){
            categtList.clear()
            categtList.addAll(categ)
            notifyDataSetChanged()
            Log.d("initDataBlock", "size  = $itemCount")
        }
    }
}

class CategoryHolder(item: View):RecyclerView.ViewHolder(item) {
        val building = CategoryItemBinding.bind(item)
        fun bind(category: Categorie){
            building.idCateg.text = category.category
        }

    }