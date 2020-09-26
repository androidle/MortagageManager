package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.databinding.MineItemInstitutionUserBinding
import com.leevinapp.monitor.mine.domain.model.InstitutionUserModel
import com.leevinapp.monitor.mine.ui.adapter.InstitutionUserAdapter.InstitutionUserViewHolder

class InstitutionUserAdapter : RecyclerView.Adapter<InstitutionUserViewHolder>() {

    private var institutions = mutableListOf<InstitutionUserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionUserViewHolder {
        val binding = MineItemInstitutionUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InstitutionUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return institutions.size
    }

    fun updateData(institutions: MutableList<InstitutionUserModel>) {
        this.institutions.clear()
        this.institutions.addAll(institutions)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: InstitutionUserViewHolder, position: Int) {
        holder.bindData(institutions[position])
    }

    inner class InstitutionUserViewHolder constructor(val binding: MineItemInstitutionUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: InstitutionUserModel) {
            binding.ivRightArrow.setOnClickListener {
                model.isExpand = model.isExpand.not()
                notifyDataSetChanged()
            }
            binding.model = model
            binding.executePendingBindings()
        }
    }
}
