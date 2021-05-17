package com.jeff.pizza.core.presentation.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T: RecyclerView.ViewHolder, R, S>(
        private val items: MutableList<S>
): RecyclerView.Adapter<T>() {

    private var _binding: R? = null
    protected val binding: R
        get() = _binding!!

    protected fun setBinding(binding: R) {
        _binding = binding
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<S>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}