package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.databinding.MineItemNotificationBinding
import com.leevinapp.monitor.mine.domain.model.NotificationModel
import com.leevinapp.monitor.mine.ui.adapter.NotificationAdapter.NotificationViewHolder

class NotificationAdapter(private val itemClick: ((NotificationModel) -> Unit)? = null) :
    RecyclerView.Adapter<NotificationViewHolder>() {

    private var notifications = mutableListOf<NotificationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun updateData(notifications: MutableList<NotificationModel>) {
        this.notifications.clear()
        this.notifications.addAll(notifications)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindData(notifications[position], itemClick)
    }

    class NotificationViewHolder private constructor(private val binding: MineItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(
            model: NotificationModel,
            itemClick: ((NotificationModel) -> Unit)?
        ) {
            binding.tvIsRead.isSelected = model.isRead
            binding.root.isSelected = model.isRead
            binding.root.setOnClickListener {
                itemClick?.invoke(model)
            }
            binding.model = model
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NotificationViewHolder {
                val binding = MineItemNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return NotificationViewHolder(binding)
            }
        }
    }
}
