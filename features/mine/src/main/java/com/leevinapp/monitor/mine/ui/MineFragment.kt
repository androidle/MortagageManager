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
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentBinding
import com.leevinapp.monitor.mine.di.DaggerMineComponent
import com.leevinapp.monitor.mine.di.MineModule
import com.leevinapp.monitor.mine.domain.model.MenuModel.ABOUT
import com.leevinapp.monitor.mine.domain.model.MenuModel.AUTHENTICATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.PASSWORD_MANAGE
import com.leevinapp.monitor.mine.domain.model.MenuModel.PERSONAL_INFORMATION
import com.leevinapp.monitor.mine.domain.model.MenuModel.PHONE_BIND
import com.leevinapp.monitor.mine.domain.model.MenuModel.SECURITY_APP
import com.leevinapp.monitor.mine.domain.model.OptionModel
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter
import com.leevinapp.monitor.mine.ui.identityauth.MineIdentityAuthSelectionFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment.*

class MineFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var selectedAuthModel: OptionModel? = null

    private lateinit var identityAuthSelectionFragment: MineIdentityAuthSelectionFragment

    val viewModel: MineViewModel by viewModels {
        viewModelFactory
    }

    private val menus = mutableListOf(
        PERSONAL_INFORMATION,
        SECURITY_APP,
        PHONE_BIND,
        AUTHENTICATION,
        PASSWORD_MANAGE,
        ABOUT
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
        DaggerMineComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .mineModule(MineModule())
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mineMenuAdapter = MineMenuAdapter(menus) {
            when (it) {
                PERSONAL_INFORMATION -> {
                    findNavController().navigate(MineFragmentDirections.mineActionMinefragmentToMinepersonalinfofragment())
                }
                SECURITY_APP -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_minesecurityfragment)
                }
                AUTHENTICATION -> {
                    if (selectedAuthModel != null) {
                        findNavController().navigate(
                            MineFragmentDirections.mineActionMinefragmentToMineauthfragment(
                                selectedAuthModel!!
                            )
                        )
                    } else {
                        // TODO: 2020/9/3 to be refactor activity result
                        // identityAuthSelectionFragment.setTargetFragment(this,TARGET_REQUEST_CODE)
                        identityAuthSelectionFragment.show(
                            childFragmentManager,
                            MineIdentityAuthSelectionFragment::class.simpleName
                        )
                    }
                }
                PASSWORD_MANAGE -> {
                }
                ABOUT -> {
                    findNavController().navigate(R.id.mine_action_minefragment_to_mineaboutfragment)
                }
            }
        }
        mineMenuAdapter.updateDate(menus)
        recycler_view.adapter = mineMenuAdapter
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(
            UiUtil.getDividerDecoration(requireContext())
        )
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
            selectedAuthModel = it
            AUTHENTICATION.content = it.name
            mineMenuAdapter.updateDate(menus)
            identityAuthSelectionFragment.dismiss()
        }
    }

    companion object {
        const val TARGET_REQUEST_CODE = 0x001
    }
}
