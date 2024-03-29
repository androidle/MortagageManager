package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineItemTicketsBinding
import com.leevinapp.monitor.mine.domain.model.TicketModel
import com.leevinapp.monitor.mine.ui.adapter.TicketsAdapter.TicketViewHolder

class TicketsAdapter : RecyclerView.Adapter<TicketViewHolder>() {

    private var tickets = mutableListOf<TicketModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = DataBindingUtil.inflate<MineItemTicketsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.mine_item_tickets,
            parent,
            false
        )
        return TicketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    fun updateData(tickets: MutableList<TicketModel>) {
        this.tickets.clear()
        this.tickets.addAll(tickets)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bindData(tickets[position])
    }

    inner class TicketViewHolder(private val binding: MineItemTicketsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: TicketModel) {
            binding.model = model
            binding.executePendingBindings()

            binding.ivRightArrow.setOnClickListener {
                model.isExpand = !model.isExpand
                notifyDataSetChanged()
            }
        }
    }
}
