package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.navigationToLogonFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import com.leevinapp.monitor.mine.domain.model.MenuModel.ACCESS_TRANSFER
import com.leevinapp.monitor.mine.domain.model.MenuModel.APPLY_REFER_ORGANIZATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.AUTH_ACCOUNT
import com.leevinapp.monitor.mine.domain.model.MenuModel.PARENT_ORGANIZATION_APPLY
import com.leevinapp.monitor.mine.ui.adapter.TextMenuAdapter
import com.leevinapp.monitor.mine.ui.identityauth.MineIdentityAuthSelectionFragment
import kotlinx.android.synthetic.main.mine_fragment.*
import javax.inject.Inject

class MineFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var identityAuthSelectionFragment: MineIdentityAuthSelectionFragment? = null

    val viewModel: MineViewModel by viewModels {
        viewModelFactory
    }

    private val menusHorizontal = mutableListOf(
        PARENT_ORGANIZATION_APPLY,
        ACCESS_TRANSFER,
        APPLY_REFER_ORGANIZATION,
        AUTH_ACCOUNT
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MineFragment.viewModel
        }.root
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_personal_info.setOnClickListener {
            findNavController().navigate(MineFragmentDirections.mineActionMinefragmentToMinepersonalinfofragment())
        }

        iv_identity_or_organ_auth.setOnClickListener {
            identityAuthSelectionFragment?.show(
                childFragmentManager,
                MineIdentityAuthSelectionFragment::class.simpleName
            )
        }

        iv_security.setOnClickListener {
            findNavController().navigate(R.id.mine_action_minefragment_to_minesecurityfragment)
        }

        iv_about.setOnClickListener {
            findNavController().navigate(R.id.mine_action_minefragment_to_mineaboutfragment)
        }

        // horizental menu
        container_menu.adapter = TextMenuAdapter(menusHorizontal) {
            // TODO: 2020/9/9
            when (it) {
                PARENT_ORGANIZATION_APPLY -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_applyparentorganfragment)
                }

                ACCESS_TRANSFER -> {

                }
                APPLY_REFER_ORGANIZATION -> {

                }

                AUTH_ACCOUNT -> {

                }

            }
        }

        btn_logout.setOnClickListener {
            userManager.reset()
            viewModel.isLogged.postValue(userManager.isLogged)
        }

        btn_goto_logon.setOnClickListener {
            findNavController().navigationToLogonFragment()
        }

        iv_avatar.setOnClickListener {
            findNavController().navigationToLogonFragment()
        }

        viewModel.isLogged.postValue(userManager.isLogged)

        if (identityAuthSelectionFragment == null) {
            identityAuthSelectionFragment =
                MineIdentityAuthSelectionFragment.newInstance(MineConstants.auth_ways)
        }
        identityAuthSelectionFragment?.setSelectedCallback { option ->
            when (option.id) {
                0 -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_ordinaryuserauthfragment)
                }
                1 -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_mortgageuserauthfragment)
                }
                2 -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_organizationauthfragment)
                }
            }
        }
    }

    companion object {
        const val TARGET_REQUEST_CODE = 0x001
    }
}
