package com.leevinapp.monitor.core.common.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.leevinapp.monitor.core.R
import kotlinx.android.synthetic.main.fragment_error_dialog.*

class ErrorDialogFragment : DialogFragment() {

    private lateinit var message: String

    companion object {
        private const val KEY_MESSAGE = "key_message"
        fun newInstance(errorMessage: String): ErrorDialogFragment {
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
            message = it.getString(KEY_MESSAGE, "unKnown error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        return inflater.inflate(R.layout.fragment_error_dialog, container, false)
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
}
