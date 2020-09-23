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
import com.leevinapp.monitor.core.core.user.UserRole.BANK_USER
import com.leevinapp.monitor.core.core.user.UserRole.BORROWER_USER
import com.leevinapp.monitor.core.core.user.UserRole.SUPERVISOR_USER
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthOrdinaryUserBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment_auth_ordinary_user.*

class OrdinaryUserAuthFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentAuthOrdinaryUserBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var identityAuthSelectionFragment: MineIdentityAuthSelectionFragment? = null

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
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivIdentityType.setOnClickListener {
            identityAuthSelectionFragment?.show(
                childFragmentManager,
                MineIdentityAuthSelectionFragment::class.simpleName
            )
        }

        if (identityAuthSelectionFragment == null) {
            identityAuthSelectionFragment =
                MineIdentityAuthSelectionFragment.newInstance(MineConstants.user_identity_types)
        }
        identityAuthSelectionFragment?.setSelectedCallback { option ->
            iv_identity_type.value = option.name
            when (option.id) {
                0 -> {
                    viewModel.setUserRole(BANK_USER)
                }
                1 -> {
                    viewModel.setUserRole(SUPERVISOR_USER)
                }
                2 -> {
                    viewModel.setUserRole(BORROWER_USER)
                }
            }
        }

        viewBinding.buttonSubmit.setOnClickListener {
            viewModel.verifyUser()
        }

        viewModel.verifyUserResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "认证成功", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        })
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
