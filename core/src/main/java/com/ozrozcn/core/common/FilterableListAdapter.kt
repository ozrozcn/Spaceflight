package com.ozrozcn.core.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozrozcn.core.BR

abstract class FilterableListAdapter<T>(
    @LayoutRes private val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, FilterableListAdapter.FilterableListAdapterViewHolder<T>>(diffCallback),
    Filterable {

    var onItemClick: ((pos: Int, item: T) -> Unit)? = null

    private var originalList: List<T> = listOf()
    private var filteredList: List<T> = listOf()

    init {
        filteredList = currentList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterableListAdapterViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return FilterableListAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterableListAdapterViewHolder<T>, position: Int) {
        holder.bind(getItem(position), onItemClick, position)
    }

    class FilterableListAdapterViewHolder<T>(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T, onClickCallback: () -> Unit) {
            with(binding) {
                setVariable(BR.item, item)
                executePendingBindings()

                root.setOnClickListener {
                    onClickCallback.invoke()
                }
            }
        }

        fun bind(item: T) {
            with(binding) {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }

        fun bind(item: T, onItemClick: ((pos: Int, T) -> Unit)?, position: Int) {
            with(binding) {
                setVariable(BR.item, item)
                executePendingBindings()

                onItemClick?.apply {
                    root.setOnClickListener {
                        onItemClick.invoke(position, item)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int = layoutId

    override fun submitList(list: List<T>?) {
        originalList = list ?: listOf()
        filteredList = originalList
        super.submitList(filteredList)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim()?.lowercase() ?: ""

                val results = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { shouldInclude(it, query) }
                }

                val filterResults = FilterResults()
                filterResults.values = results
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<T>
                submitFilteredList(filteredList)
            }
        }
    }

    abstract fun shouldInclude(item: T, query: String): Boolean

    private fun submitFilteredList(list: List<T>) {
        super.submitList(list)
    }
}
