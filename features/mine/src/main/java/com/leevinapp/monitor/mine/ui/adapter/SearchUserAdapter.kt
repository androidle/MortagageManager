package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.core.core.user.UserModel
import com.leevinapp.monitor.mine.databinding.MineItemSearchUserResultBinding
import com.leevinapp.monitor.mine.ui.adapter.SearchUserAdapter.SearchUserViewHolder

class SearchUserAdapter : RecyclerView.Adapter<SearchUserViewHolder>() {

    private var resultList = mutableListOf<UserModel>()

    private var itemClick: ((UserModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val binding =
            MineItemSearchUserResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateResult(result: List<UserModel>) {
        resultList.clear()
        resultList.addAll(result)
        notifyDataSetChanged()
    }

    fun clear() {
        resultList.clear()
        notifyDataSetChanged()
    }

    fun setItemClick(callback: ((UserModel) -> Unit)?) {
        this.itemClick = callback
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        holder.bind(resultList[position], itemClick)
    }

    inner class SearchUserViewHolder constructor(val binding: MineItemSearchUserResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: UserModel,
            itemClick: ((UserModel) -> Unit)?
        ) {
            binding.root.setOnClickListener {
                itemClick?.invoke(model)
            }
            binding.model = model
            binding.executePendingBindings()
        }
    }
}
