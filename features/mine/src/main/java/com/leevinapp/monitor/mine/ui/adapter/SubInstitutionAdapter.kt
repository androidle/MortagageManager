package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.ui.adapter.SubInstitutionAdapter.SubInstitutionViewHolder
import kotlinx.android.synthetic.main.mine_item_sub_institution.view.*

class SubInstitutionAdapter : RecyclerView.Adapter<SubInstitutionViewHolder>() {

    private var institutions = mutableListOf<InstitutionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubInstitutionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mine_item_sub_institution, parent, false)
        return SubInstitutionViewHolder(view)
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

    inner class SubInstitutionViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(model: InstitutionModel) {
            with(itemView) {
                tv_title_name.text = model.institutionName
                tv_title_name.setOnClickListener {
                    model.isExpand = model.isExpand.not()
                    notifyDataSetChanged()
                }

                if (model.isExpand) {
                    container_content.visibility = View.VISIBLE
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_down)
                    drawable?.let {
                        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                        tv_title_name.setCompoundDrawables(null, null, drawable, null)
                    }
                } else {
                    container_content.visibility = View.GONE
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_right)
                    drawable?.let {
                        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                        tv_title_name.setCompoundDrawables(null, null, drawable, null)
                    }
                }
            }
        }
    }
}
