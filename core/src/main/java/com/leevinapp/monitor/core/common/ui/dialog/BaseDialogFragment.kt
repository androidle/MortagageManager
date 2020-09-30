package com.leevinapp.monitor.core.common.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.core.utils.UiUtil
import com.leevinapp.monitor.core.core.utils.autoCleared

abstract class BaseDialogFragment<B : ViewDataBinding> : DialogFragment() {

    var viewBinding by autoCleared<B>()

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CommonDialogStyle)
    }

    override fun onStart() {
        super.onStart()
        resizeDialogFragment()
    }

    private fun resizeDialogFragment() {
        dialog?.let {
            val window = it.window!!
            val attr = window.attributes
            attr.width = UiUtil.getScreenSize(requireContext())[0] * 8 / 10
            window.setLayout(attr.width, LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<B>(inflater, getLayoutId(), container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
            initView(viewBinding.root)
        }.root
    }

    open fun initView(view: View) {}

    fun getFragmentTag(): String = this.javaClass.simpleName

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, getFragmentTag())
    }

    companion object {
        private const val DEFAULT_DIM = 0.2f
    }
}
