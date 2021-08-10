package ru.kavunov.mtsproject.adapter


import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kavunov.mtsproject.DTC.Actors

import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.databinding.ItemActorBinding

class ActorsAdapter(): RecyclerView.Adapter<ActorsHolder>() {
    var actorstList: MutableList<Actors> = ArrayList()




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

    fun initData(actor: List<Actors>?) {
        if (actor!=null){
            actorstList.clear()
            actorstList.addAll(actor)
            notifyDataSetChanged()
            Log.d("initDataBlock", "size  = $itemCount")
        }
    }

}


class ActorsHolder(item: View): RecyclerView.ViewHolder(item) {

    val building = ItemActorBinding.bind(item)
    fun bind(actors: Actors){
        building.nameActorId.text = actors.name
        building.imgActorID.load(actors.img)

    }

}