package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.databinding.MineItemSearchResultBinding
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.domain.model.SearchResult
import com.leevinapp.monitor.mine.ui.adapter.SearchResultAdapter.SearchResultViewHolder

class SearchResultAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {

    private var resultList = mutableListOf<SearchResult>()

    private var itemClick: ((InstitutionModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            MineItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateResult(result: List<InstitutionModel>) {
        resultList.clear()
        resultList.addAll(result)
        notifyDataSetChanged()
    }

    fun clear() {
        resultList.clear()
        notifyDataSetChanged()
    }

    fun setItemClick(callback: ((InstitutionModel) -> Unit)?) {
        this.itemClick = callback
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(resultList[position] as InstitutionModel, itemClick)
    }

    inner class SearchResultViewHolder constructor(val binding: MineItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            searchResult: InstitutionModel,
            itemClick: ((InstitutionModel) -> Unit)?
        ) {
            binding.root.setOnClickListener {
                itemClick?.invoke(searchResult)
            }
            binding.model = searchResult
            binding.executePendingBindings()
        }
    }
}
