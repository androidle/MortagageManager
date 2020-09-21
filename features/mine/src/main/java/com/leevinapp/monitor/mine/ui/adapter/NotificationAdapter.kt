package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.NotificationModel
import com.leevinapp.monitor.mine.ui.adapter.NotificationAdapter.NotificationViewHolder
import kotlinx.android.synthetic.main.mine_item_notification.view.*

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

    class NotificationViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(
            model: NotificationModel,
            itemClick: ((NotificationModel) -> Unit)?
        ) {
            with(itemView) {
                tv_title.text = model.title
                tv_isRead.isSelected = model.isRead
                itemView.isSelected = model.isRead
                if (model.isRead) {
                    tv_isRead.text = context.getString(R.string.mine_notification_read)
                } else {
                    tv_isRead.text = context.getString(R.string.mine_notification_unread)
                }

                tv_data.text = model.date
                tv_applicant.text =
                    context.getString(R.string.mine_applicant, model.applicant)
                tv_phone_number.text = model.phoneNumber

                setOnClickListener {
                    itemClick?.invoke(model)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): NotificationViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.mine_item_notification, parent, false)
                return NotificationViewHolder(view)
            }
        }
    }
}
