package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.OptionModel
import kotlinx.android.synthetic.main.mine_fragment_identity_auth_selection.view.*

class MineIdentityAuthSelectionFragment : BottomSheetDialogFragment() {

    private var callback: ((OptionModel) -> Unit)? = null

    private var selectedPosition: Int = 0

    private lateinit var stringArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stringArray = arguments?.getStringArray(KEY_CONTENTS) as Array<String>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.mine_fragment_identity_auth_selection, container, false)
        root.picker.displayedValues = stringArray
        root.picker.minValue = 0
        root.picker.maxValue = stringArray.size - 1
        root.picker.setFormatter {
            stringArray[it]
        }
        root.picker.setOnValueChangedListener { _, _, newVal ->
            selectedPosition = newVal
        }

        root.tv_cancel.setOnClickListener {
            dismiss()
        }

        root.tv_confirm.setOnClickListener {
            dismiss()
            val selectedModel = OptionModel(selectedPosition, stringArray[selectedPosition])
            callback?.invoke(selectedModel)
        }

        return root
    }

    fun setSelectedCallback(callback: (OptionModel) -> Unit) {
        this.callback = callback
    }

    companion object {
        const val KEY_CONTENTS = "KEY_CONTENTS"
        fun newInstance(string: Array<String>): MineIdentityAuthSelectionFragment {
            return MineIdentityAuthSelectionFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(KEY_CONTENTS, string)
                }
            }
        }
    }
}
