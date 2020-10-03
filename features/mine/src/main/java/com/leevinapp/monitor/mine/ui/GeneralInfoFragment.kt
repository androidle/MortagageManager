package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentGeneralInfoBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import com.leevinapp.monitor.mine.ui.identityauth.MineIdentityAuthSelectionFragment
import javax.inject.Inject

class GeneralInfoFragment : ViewModelFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: GeneralInfoViewModel by viewModels {
        viewModelFactory
    }

    private var viewBinding by autoCleared<MineFragmentGeneralInfoBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentGeneralInfoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@GeneralInfoFragment.viewModel
            userManager = this@GeneralInfoFragment.userManager
            viewBinding = this
        }.root
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivPersonalInfo.setOnClickListener {
            findNavController().navigate(R.id.mine_action_mine_minegeneralinfofragment_to_minepersonalinfofragment)
        }

        viewBinding.ivIdentityOrOrganAuth.setOnClickListener {
            showIdentitySelectionPage()
        }

        viewBinding.ivSecurity.setOnClickListener {
            findNavController().navigate(R.id.mine_action_mine_minegeneralinfofragment_to_minesecurityfragment)
        }

        viewBinding.ivAbout.setOnClickListener {
            findNavController().navigate(R.id.mine_action_mine_minegeneralinfofragment_to_mineaboutfragment)
        }

        viewBinding.btnLogout.setOnClickListener {
            viewModel.logout()
            userManager.reset()
            findNavController().navigateUp()
        }
    }

    private fun showIdentitySelectionPage() {
        MineIdentityAuthSelectionFragment.newInstance(MineConstants.auth_ways)
            .apply {
                setSelectedCallback { option ->
                    when (option.id) {
                        0 -> {
                            findNavController().navigate(R.id.mine_action_mine_minegeneralinfofragment_to_ordinaryuserauthfragment)
                        }
                        1 -> {
                            findNavController().navigate(R.id.mine_action_mine_minegeneralinfofragment_to_mortgageuserauthfragment)
                        }
                        2 -> {
                            findNavController().navigate(R.id.mine_action_mine_minegeneralinfofragment_to_organizationauthfragment)
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

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }
}
