package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.common.UiUtil
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.navigationToLogonFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.model.MenuModel.ABOUT
import com.leevinapp.monitor.mine.domain.model.MenuModel.ACCESS_TRANSFER
import com.leevinapp.monitor.mine.domain.model.MenuModel.APPLY_REFER_ORGANIZATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.AUTHENTICATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.AUTH_ACCOUNT
import com.leevinapp.monitor.mine.domain.model.MenuModel.PARENT_ORGANIZATION_APPLY
import com.leevinapp.monitor.mine.domain.model.MenuModel.PERSONAL_INFORMATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.SECURITY_APP
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter
import com.leevinapp.monitor.mine.ui.adapter.TextMenuAdapter
import com.leevinapp.monitor.mine.ui.identityauth.MineIdentityAuthSelectionFragment
import kotlinx.android.synthetic.main.mine_fragment.*
import javax.inject.Inject

class MineFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var identityAuthSelectionFragment: MineIdentityAuthSelectionFragment

    val viewModel: MineViewModel by viewModels {
        viewModelFactory
    }

    private val menusVertical = mutableListOf(
        PERSONAL_INFORMATION,
        SECURITY_APP,
        AUTHENTICATION,
        ABOUT
    )

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
        val mineMenuAdapter = MineMenuAdapter(menusVertical) {
            when (it) {
                PERSONAL_INFORMATION -> {
                    findNavController().navigate(MineFragmentDirections.mineActionMinefragmentToMinepersonalinfofragment())
                }
                SECURITY_APP -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_minesecurityfragment)
                }
                AUTHENTICATION -> {
                    // TODO: 2020/9/3 to be refactor activity result
                    // identityAuthSelectionFragment.setTargetFragment(this,TARGET_REQUEST_CODE)
                    identityAuthSelectionFragment.show(
                        childFragmentManager,
                        MineIdentityAuthSelectionFragment::class.simpleName
                    )
                }
                ABOUT -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_mineaboutfragment)
                }
            }
        }
        recycler_view.adapter = mineMenuAdapter
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(
            UiUtil.getDividerDecoration(requireContext())
        )

        // horizental menu
        container_menu.adapter = TextMenuAdapter(menusHorizontal) {
            // TODO: 2020/9/9
        }

        btn_logout.setOnClickListener {
            userManager.reset()
            viewModel.isLogged.postValue(userManager.isLogged)
        }


        btn_goto_logon.setOnClickListener {
            // userManager.isLogged = true
            // viewModel.isLogged.postValue(userManager.isLogged)
            findNavController().navigationToLogonFragment()
        }

        viewModel.isLogged.postValue(userManager.isLogged)

        identityAuthSelectionFragment =
            MineIdentityAuthSelectionFragment()
        identityAuthSelectionFragment.setSelectedCallback {
            AUTHENTICATION.content = it.name
            findNavController().navigate(
                MineFragmentDirections.mineActionMinefragmentToMineauthfragment(it)
            )
            identityAuthSelectionFragment.dismiss()
        }
    }

    companion object {
        const val TARGET_REQUEST_CODE = 0x001
    }
}
