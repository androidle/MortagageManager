package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.MenuModel
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter.MenuViewHolder
import kotlinx.android.synthetic.main.mine_item_menu.view.*

class MineMenuAdapter(
    private val menuList: MutableList<MenuModel>,
    private val clickListener: ((MenuModel) -> Unit)? = null
) : RecyclerView.Adapter<MenuViewHolder>() {

    private val data = mutableListOf<MenuModel>()

    init {
        updateDate(menuList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindData(data[position], clickListener)
    }

    fun updateDate(menus: MutableList<MenuModel>) {
        data.clear()
        data.addAll(menus)
        notifyDataSetChanged()
    }

    class MenuViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(menuModel: MenuModel, clickListener: ((MenuModel) -> Unit)?) {
            itemView.tv_name.text = menuModel.value
            itemView.tv_value.text = menuModel.content
            if (menuModel.content.isNotEmpty()) {
                itemView.tv_value.visibility = View.VISIBLE
            } else {
                itemView.tv_value.visibility = View.GONE
            }
            itemView.setOnClickListener {
                clickListener?.invoke(menuModel)
            }
        }

        companion object {
            fun from(parent: ViewGroup): MenuViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.mine_item_menu, parent, false)
                return MenuViewHolder(view)
            }
        }
    }
}
