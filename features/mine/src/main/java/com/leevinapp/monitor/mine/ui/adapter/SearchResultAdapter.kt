package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.domain.model.SearchResult
import com.leevinapp.monitor.mine.ui.adapter.SearchResultAdapter.SearchResultViewHolder
import kotlinx.android.synthetic.main.mine_item_search_result.view.*

class SearchResultAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {

    private var resultList = mutableListOf<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mine_item_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateResult(result: List<InstitutionModel>) {
        resultList.clear()
        resultList.addAll(result)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    inner class SearchResultViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(searchResult: SearchResult) {
            itemView.tv_name.text = searchResult.getName()
            itemView.tv_value.text = searchResult.getValue()
        }
    }
}
