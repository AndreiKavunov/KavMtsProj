package ru.kavunov.mtsproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.databinding.ItemActorBinding

class ActorsAdapter(ListMain: List<Actors>): RecyclerView.Adapter<ActorsHolder>() {
    var actorstList = ListMain



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorsHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsHolder, position: Int) {
        holder.bind(actorstList[position])
    }

    override fun getItemCount(): Int {
        return actorstList.size
    }
}


class ActorsHolder(item: View): RecyclerView.ViewHolder(item) {

    val building = ItemActorBinding.bind(item)
    fun bind(actors: Actors){
        building.nameActorId.text = actors.name
        building.imgActorID.load(actors.img)

    }

}