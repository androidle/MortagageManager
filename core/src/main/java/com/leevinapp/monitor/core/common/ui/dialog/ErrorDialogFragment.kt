package com.leevinapp.monitor.core.common.ui.dialog

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.fragment.app.DialogFragment
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.core.utils.UiUtil
import kotlinx.android.synthetic.main.fragment_error_dialog.*

class ErrorDialogFragment : DialogFragment() {

    private var message: String? = null

    private var root: View? = null

    companion object {
        private const val KEY_MESSAGE = "key_message"
        fun newInstance(errorMessage: String?): ErrorDialogFragment {
            return ErrorDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_MESSAGE, errorMessage)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(KEY_MESSAGE, message)
        }

        setStyle(STYLE_NO_FRAME, R.style.CommonDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_error_dialog, container, false)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_message.text = message
        button_positive.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    fun setMessage(message: String) {
        this.message = message
    }

    override fun onStart() {
        super.onStart()
        resizeDialogFragment()
    }

    private fun resizeDialogFragment() {
        dialog?.let {
            val window = it.window!!
            val lp = window.attributes
            lp.width = UiUtil.getScreenSize(requireContext())[0] * 8 / 10
            window.setLayout(lp.width, LayoutParams.WRAP_CONTENT)
        }
    }

    private val SAVED_DIALOG_STATE_TAG = "android:savedDialogState"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if (showsDialog) {
            showsDialog = false
        }
        super.onActivityCreated(savedInstanceState)
        showsDialog = true
        val view = view
        if (view != null) {
            check(view.parent == null) { "DialogFragment can not be attached to a container view" }
            dialog!!.setContentView(view)
        }
        val activity: Activity? = activity
        if (activity != null) {
            dialog!!.setOwnerActivity(activity)
        }
        if (savedInstanceState != null) {
            val dialogState = savedInstanceState.getBundle(SAVED_DIALOG_STATE_TAG)
            if (dialogState != null) {
                dialog!!.onRestoreInstanceState(dialogState)
            }
        }
    }
}
