package com.leevinapp.monitor.core.common.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.R
import timber.log.Timber

abstract class BaseFragment : Fragment(), Injector, WithToolbar {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("${this.javaClass.simpleName}====onCreate=====")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("${this.javaClass.simpleName}====onViewCreated=====")
        initTitleBarView()
    }

    private fun initTitleBarView() {
        (getTitleBarView() as? ConstraintLayout)?.let { toolbar ->
            val toolbarTitle = getTitleBarTitle()
            val ivBack = toolbar.findViewById(R.id.iv_back) as ImageView
            val toolbarTitleTextView = toolbar.findViewById(R.id.tv_title) as TextView
            val toolbarRightIcon = toolbar.findViewById(R.id.tv_right) as TextView
            if (toolbarTitle.isNotEmpty()) {
                toolbarTitleTextView.visibility = View.VISIBLE
                toolbarTitleTextView.text = toolbarTitle
            } else {
                toolbarTitleTextView.visibility = View.GONE
            }

            if (isShowRightActionText()) {
                toolbarRightIcon.visibility = View.VISIBLE
                toolbarRightIcon.setOnClickListener {
                    onRightTextClick()
                }
            } else {
                toolbarRightIcon.visibility = View.GONE
            }

            if (isShowBackIcon()) {
                ivBack.visibility = View.VISIBLE
                ivBack.setOnClickListener {
                    findNavController().navigateUp()
                }
            } else {
                ivBack.visibility = View.GONE
            }
        }
    }

    override fun onAttach(context: Context) {
        initDependencyInjection()
        super.onAttach(context)
    }

    open fun onRightTextClick(){

    }


    override fun initDependencyInjection() {
    }
}
