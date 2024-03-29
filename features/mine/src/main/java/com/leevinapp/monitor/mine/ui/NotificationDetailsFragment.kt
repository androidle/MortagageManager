package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentNotificationDetailsBinding
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class NotificationDetailsFragment : BaseFragment() {

    private lateinit var viewBinding: MineFramentNotificationDetailsBinding

    val args: NotificationDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentNotificationDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            dataModel = args.model
            userManager = this@NotificationDetailsFragment.userManager
            viewBinding = this
        }.root
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_notification)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
