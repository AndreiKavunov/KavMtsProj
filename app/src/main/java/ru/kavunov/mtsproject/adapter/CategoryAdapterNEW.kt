package ru.kavunov.mtsproject.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.databinding.CategoryItemBinding
import java.util.ArrayList

class CategoryAdapterNEW():RecyclerView.Adapter<CategoryHolderNEW>() {
    var categtList: MutableList<CategoryTable> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolderNEW {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolderNEW(view)
    }

    override fun onBindViewHolder(holder: CategoryHolderNEW, position: Int) {
        holder.bind(categtList[position])
    }

    override fun getItemCount(): Int {
        return categtList.size
    }
    fun initData(categ: List<CategoryTable>?) {
        if (categ!=null){
            categtList.clear()
            categtList.addAll(categ)
            notifyDataSetChanged()

        }
    }
}

class CategoryHolderNEW(item: View):RecyclerView.ViewHolder(item) {
    val building = CategoryItemBinding.bind(item)
    fun bind(category: CategoryTable){
        building.idCateg.text = category.category
    }

}