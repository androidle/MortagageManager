package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.user.UserRole.BANK_USER_NO_ORG
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthMortgageUserBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import javax.inject.Inject

class MortgageUserAuthFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentAuthMortgageUserBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: IdentityAuthViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentAuthMortgageUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MortgageUserAuthFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUserRole(BANK_USER_NO_ORG)

        viewBinding.buttonSubmit.setOnClickListener {
            viewModel.verifyUser()
        }
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return MineConstants.auth_ways[1]
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
