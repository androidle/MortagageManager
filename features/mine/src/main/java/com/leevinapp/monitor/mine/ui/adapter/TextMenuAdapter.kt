package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.MenuModel
import com.leevinapp.monitor.mine.ui.adapter.TextMenuAdapter.TextMenuViewHolder
import kotlinx.android.synthetic.main.mine_item_menu_text.view.*

class TextMenuAdapter(
    private val menuList: MutableList<MenuModel>,
    private val clickListener: ((MenuModel) -> Unit)? = null
) : RecyclerView.Adapter<TextMenuViewHolder>() {

    private val data = mutableListOf<MenuModel>()

    init {
        updateDate(menuList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextMenuViewHolder {
        return TextMenuViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TextMenuViewHolder, position: Int) {
        holder.bindData(data[position], clickListener)
    }

    fun updateDate(menus: MutableList<MenuModel>) {
        data.clear()
        data.addAll(menus)
        notifyDataSetChanged()
    }

    class TextMenuViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(menuModel: MenuModel, clickListener: ((MenuModel) -> Unit)?) {
            itemView.tv_text.text = menuModel.value
            itemView.setOnClickListener {
                clickListener?.invoke(menuModel)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TextMenuViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.mine_item_menu_text, parent, false)
                return TextMenuViewHolder(view)
            }
        }
    }

}
