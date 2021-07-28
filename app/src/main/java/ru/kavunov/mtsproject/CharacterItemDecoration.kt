package ru.kavunov.mtsproject

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View

import androidx.recyclerview.widget.RecyclerView


class CharacterItemDecoration(private val fotoW: Int) :
RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        s: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val indent_h = parent.width/2 - fotoW

            .let { if (it == RecyclerView.NO_POSITION) return else it }
            if (position % 2 == 0) rect.right = 20
            else rect.left = indent_h
    }
}