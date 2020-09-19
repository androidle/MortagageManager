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
import com.leevinapp.monitor.mine.domain.model.MenuModel.ACCESS_TRANSFER
import com.leevinapp.monitor.mine.domain.model.MenuModel.APPLY_REFER_ORGANIZATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.AUTH_ACCOUNT
import com.leevinapp.monitor.mine.domain.model.MenuModel.PARENT_ORGANIZATION_APPLY
import com.leevinapp.monitor.mine.ui.adapter.TextMenuAdapter
import kotlinx.android.synthetic.main.mine_fragment.*
import javax.inject.Inject

class MineFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        // horizontal menu
        container_menu.adapter = TextMenuAdapter(menusHorizontal) {
            when (it) {
                PARENT_ORGANIZATION_APPLY -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_applyparentorganfragment)
                }
                ACCESS_TRANSFER -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_accesstransferfragment)
                }
                APPLY_REFER_ORGANIZATION -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_applyattachedinstitutionfragment)
                }
                AUTH_ACCOUNT -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_authaccountfragment)
                }

            }
        }

        iv_apply_sheets.setOnClickListener {
            findNavController().navigate(R.id.mine_action_minefragment_to_applysheetsfragment)
        }

        iv_notification.setOnClickListener {
            findNavController().navigate(R.id.mine_action_minefragment_to_notificationsfragment)
        }

        iv_sub_institution.setOnClickListener {
            findNavController().navigate(R.id.mine_action_minefragment_to_subinstitutionsfragment)
        }

        iv_institution_user.setOnClickListener {
            findNavController().navigate(R.id.mine_action_minefragment_to_institutionuserfragment)
        }

        btn_goto_logon.setOnClickListener {
            findNavController().navigationToLogonFragment()
        }

        iv_avatar.setOnClickListener {
            if (userManager.isLogged) {
                findNavController().navigate(R.id.mine_action_minefragment_to_minegeneralinfofragment)
            } else {
                findNavController().navigationToLogonFragment()
            }

        }

        viewModel.isLogged.postValue(userManager.isLogged)
    }

    companion object {
        const val TARGET_REQUEST_CODE = 0x001
    }
}
