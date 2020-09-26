package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.databinding.MineItemSubInstitutionBinding
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.ui.adapter.SubInstitutionAdapter.SubInstitutionViewHolder

class SubInstitutionAdapter : RecyclerView.Adapter<SubInstitutionViewHolder>() {

    private var institutions = mutableListOf<InstitutionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubInstitutionViewHolder {
        val binding = MineItemSubInstitutionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubInstitutionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return institutions.size
    }

    fun updateData(institutions: MutableList<InstitutionModel>) {
        this.institutions.clear()
        this.institutions.addAll(institutions)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SubInstitutionViewHolder, position: Int) {
        holder.bindData(institutions[position])
    }

    inner class SubInstitutionViewHolder constructor(val binding: MineItemSubInstitutionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: InstitutionModel) {
            binding.model = model
            binding.tvTitleName.setOnClickListener {
                model.isExpand = model.isExpand.not()
                notifyDataSetChanged()
            }
            binding.executePendingBindings()
        }
    }
}
