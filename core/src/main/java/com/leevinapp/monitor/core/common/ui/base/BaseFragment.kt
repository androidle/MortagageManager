package com.leevinapp.monitor.core.common.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
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
        initToolbar()
    }

    private fun initToolbar() {
        (getToolbar() as? MaterialToolbar)?.let { toolbar ->
            val toolbarTitle = getToolbarTitle()
            val toolbarTitleTextView = toolbar.findViewById(R.id.tv_toolbar_title) as TextView
            val toolbarRightIcon = toolbar.findViewById(R.id.iv_toolbar_right) as ImageView
            if (toolbarTitle.isNotEmpty()) {
                toolbarTitleTextView.visibility = View.VISIBLE
                toolbarTitleTextView.text = toolbarTitle
            } else {
                toolbarTitleTextView.visibility = View.GONE
            }

            if (isShowRightIcon()) {
                toolbarRightIcon.visibility = View.VISIBLE
                if (getToolBarRightIconRes() != 0) {
                    toolbarRightIcon.setImageResource(getToolBarRightIconRes())
                }
            } else {
                toolbarRightIcon.visibility = View.GONE
            }

            if (isShowBackIcon()) {
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            } else {
                toolbar.navigationIcon = null
            }
        }
    }

    override fun onAttach(context: Context) {
        initDependencyInjection()
        super.onAttach(context)
    }

    override fun initDependencyInjection() {
    }
}
