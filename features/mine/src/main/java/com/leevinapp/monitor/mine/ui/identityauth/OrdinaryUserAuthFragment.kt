package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.user.UserRole.BANK_USER
import com.leevinapp.monitor.core.core.user.UserRole.BORROWER_USER
import com.leevinapp.monitor.core.core.user.UserRole.SUPERVISOR_USER
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthOrdinaryUserBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import javax.inject.Inject

class OrdinaryUserAuthFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentAuthOrdinaryUserBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userManager: UserManager

    val viewModel: IdentityAuthViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentAuthOrdinaryUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@OrdinaryUserAuthFragment.viewModel
            userManager = this@OrdinaryUserAuthFragment.userManager
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUserRole(BANK_USER)

        viewBinding.ivIdentityType.setOnClickListener {
            showIdentitySelectionPage()
        }

        viewBinding.buttonSubmit.setOnClickListener {
            viewModel.verifyOrdinaryUser()
        }

        viewModel.verifyUserResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "认证成功", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        })
    }

    private fun showIdentitySelectionPage() {
        MineIdentityAuthSelectionFragment.newInstance(MineConstants.user_identity_types)
            .apply {
                setSelectedCallback { option ->
                    viewBinding.ivIdentityType.value = option.name
                    when (option.id) {
                        0 -> {
                            viewModel.setUserRole(BANK_USER)
                        }
                        1 -> {
                            viewModel.setUserRole(BORROWER_USER)
                        }
                        2 -> {
                            viewModel.setUserRole(SUPERVISOR_USER)
                        }
                    }
                }
            }
            .show(
                childFragmentManager,
                MineIdentityAuthSelectionFragment::class.simpleName
            )
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return MineConstants.auth_ways[0]
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
