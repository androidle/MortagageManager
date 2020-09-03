package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.R.layout
import com.leevinapp.monitor.mine.di.DaggerMineComponent
import com.leevinapp.monitor.mine.di.MineModule
import com.leevinapp.monitor.mine.domain.model.MenuModel.ABOUT
import com.leevinapp.monitor.mine.domain.model.MenuModel.AUTHENTICATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.PASSWORD_MANAGE
import com.leevinapp.monitor.mine.domain.model.MenuModel.PERSONAL_INFORMATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.PHONE_BIND
import com.leevinapp.monitor.mine.domain.model.MenuModel.SECURITY_APP
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment.*

class MineFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val menus = mutableListOf(
        PERSONAL_INFORMATION,
        SECURITY_APP,
        PHONE_BIND,
        AUTHENTICATION,
        PASSWORD_MANAGE,
        ABOUT
    )

    val viewModel: MineViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.mine_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = MineMenuAdapter(menus) {
            when (it) {
                PERSONAL_INFORMATION -> {
                    // TODO: 2020/9/1
                }
                SECURITY_APP -> {
                }
                AUTHENTICATION -> {
                }
                PASSWORD_MANAGE -> {
                }
                ABOUT -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_mineaboutfragment)
                }
            }
        }
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .colorResId(R.color.color_monitor_pewter)
                .sizeResId(R.dimen.dimen_divider)
                .build()
        )

        btn_logout.setOnClickListener {
            userManager.reset()
            findNavController().navigate(R.id.mineFragment)
        }

        tv_unlogon.setOnClickListener {
            userManager.isLogged = true
            findNavController().navigate(R.id.mineFragment)
        }

        iv_unlogon_avatar.setOnClickListener {
            userManager.isLogged = true
            findNavController().navigate(R.id.mineFragment)
        }

        if (userManager.isLogged) {
            container_post_logon.visibility = View.VISIBLE
            container_unlogon.visibility = View.GONE
        } else {
            container_post_logon.visibility = View.GONE
            container_unlogon.visibility = View.VISIBLE
        }
    }

    override fun initDependencyInjection() {
        DaggerMineComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .mineModule(MineModule())
            .build()
            .inject(this)
    }
}
