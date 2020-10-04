package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.ui.dialog.SearchDialogFragment
import com.leevinapp.monitor.core.common.ui.extensions.navigationToLogonFragment
import com.leevinapp.monitor.core.common.ui.extensions.showErrorDialog
import com.leevinapp.monitor.core.core.config.Constants
import com.leevinapp.monitor.core.core.storage.Storage
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.BuildConfig
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
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment.*

class MineFragment : ViewModelFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var storage: Storage

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
                    showIdentitySelectionPage()
                }
                else -> {}
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

        debugFeature()

        viewModel.isLogged.postValue(userManager.isLogged)

        if (userManager.isLogged) {
            viewModel.getTicketInfo()
        }

        iv_settings.setOnClickListener {
            requireActivity().showErrorDialog("Settings")
            SearchDialogFragment().show(childFragmentManager, SearchDialogFragment::class.simpleName)
        }
    }

    private fun debugFeature() {
        if (BuildConfig.DEBUG) {
            iv_avatar.setOnLongClickListener(object : OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {
                    val isNoNeedLogon = storage.getBoolean(Constants.KEY_IS_NO_NEED_LOGON)
                    storage.setBoolean(Constants.KEY_IS_NO_NEED_LOGON, !isNoNeedLogon)
                    Toast.makeText(
                        requireContext(),
                        if (!isNoNeedLogon) "开启免登录" else "关闭免登录",
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
            })
        }
    }

    private fun showIdentitySelectionPage() {
        MineIdentityAuthSelectionFragment.newInstance(MineConstants.auth_ways)
            .apply {
                setSelectedCallback { option ->
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
            .show(
                childFragmentManager,
                MineIdentityAuthSelectionFragment::class.simpleName
            )
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
