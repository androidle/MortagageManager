package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.OptionModel
import kotlinx.android.synthetic.main.mine_fragment_identity_auth_selection.*
import kotlinx.android.synthetic.main.mine_fragment_identity_auth_selection.view.*

class MineIdentityAuthSelectionFragment : BottomSheetDialogFragment() {

    private var callback: ((OptionModel) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.mine_fragment_identity_auth_selection, container, false)
        root.tv_option1.setOnClickListener {
            callback?.invoke(OptionModel(name = tv_option1.text.toString()))
        }
        root.tv_option2.setOnClickListener {
            callback?.invoke(OptionModel(name = tv_option2.text.toString()))
        }
        root.tv_option3.setOnClickListener {
            callback?.invoke(OptionModel(name = tv_option3.text.toString()))
        }
        return root
    }

    fun setSelectedCallback(callback: (OptionModel) -> Unit) {
        this.callback = callback
    }
}
