package com.leevinapp.monitor.mine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.ui.adapter.InstitutionUserAdapter.InstitutionUserViewHolder
import kotlinx.android.synthetic.main.mine_item_institution_user.view.*
import kotlinx.android.synthetic.main.mine_item_sub_institution.view.container_content
import kotlinx.android.synthetic.main.mine_item_sub_institution.view.tv_title_name

class InstitutionUserAdapter : RecyclerView.Adapter<InstitutionUserViewHolder>() {

    private var institutions = mutableListOf<InstitutionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionUserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mine_item_institution_user, parent, false)
        return InstitutionUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return institutions.size
    }

    fun updateData(institutions: MutableList<InstitutionModel>) {
        this.institutions.clear()
        this.institutions.addAll(institutions)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: InstitutionUserViewHolder, position: Int) {
        holder.bindData(institutions[position])
    }

    inner class InstitutionUserViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(model: InstitutionModel) {
            with(itemView) {
                tv_title_name.text = model.username
                tv_sub_title.text = model.userrole
                iv_right_arrow.setOnClickListener {
                    model.isExpand = model.isExpand.not()
                    notifyDataSetChanged()
                }

                if (model.isExpand) {
                    container_content.visibility = View.VISIBLE
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_down)
                    iv_right_arrow.setImageDrawable(drawable)
                } else {
                    container_content.visibility = View.GONE
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_right)
                    iv_right_arrow.setImageDrawable(drawable)
                }
            }
        }
    }
}