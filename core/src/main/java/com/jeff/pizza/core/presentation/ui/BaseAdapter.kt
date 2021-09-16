package com.jeff.pizza.core.presentation.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder, R>(
    private val items: MutableList<R>
) : RecyclerView.Adapter<T>() {

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<R>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}