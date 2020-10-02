package com.leevinapp.monitor.project.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.project.databinding.ProjectItemProjectBinding
import com.leevinapp.monitor.project.domain.model.ProjectModel
import com.leevinapp.monitor.project.ui.adapter.ProjectAdapter.ProjectViewHolder

class ProjectAdapter : RecyclerView.Adapter<ProjectViewHolder>() {

    private var data = mutableListOf<ProjectModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding =
            ProjectItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    fun updateData(institutions: MutableList<ProjectModel>) {
        this.data.clear()
        this.data.addAll(institutions)
        notifyDataSetChanged()
    }

    inner class ProjectViewHolder constructor(val binding: ProjectItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: ProjectModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}
