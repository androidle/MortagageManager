package com.leevinapp.monitor.mine.ui.identityauth

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
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
import com.leevinapp.monitor.core.core.user.UserRole.BANK
import com.leevinapp.monitor.core.core.user.UserRole.BORROWER
import com.leevinapp.monitor.core.core.user.UserRole.SUPERVISOR
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthOrganizationBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import java.util.Calendar
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment_auth_organization.*

class OrganizationAuthFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentAuthOrganizationBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userManager: UserManager

    val viewModel: IdentityAuthViewModel by viewModels {
        viewModelFactory
    }

    private var identityAuthSelectionFragment: MineIdentityAuthSelectionFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentAuthOrganizationBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            userManager = this@OrganizationAuthFragment.userManager
            viewModel = this@OrganizationAuthFragment.viewModel
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
                MineIdentityAuthSelectionFragment.newInstance(MineConstants.organ_identity_types)
        }
        identityAuthSelectionFragment?.setSelectedCallback { option ->
            iv_identity_type.value = option.name
            when (option.id) {
                0 -> {
                    viewModel.setUserRole(BANK)
                }
                1 -> {
                    viewModel.setUserRole(BORROWER)
                }
                2 -> {
                    viewModel.setUserRole(SUPERVISOR)
                }
            }
        }

        viewBinding.buttonSubmit.setOnClickListener {
            viewModel.verifyOrganization()
        }

        viewModel.verifyUserResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "认证成功", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        })

        initDatePicker()
    }

    private var currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    private var currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    private var currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    private fun initDatePicker() {
        viewBinding.ievDateOfFound.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                OnDateSetListener { view, year, month, dayOfMonth ->
                    viewBinding.ievDateOfFound.value = "$year-${month + 1}-$dayOfMonth"
                    currentYear = currentYear
                    currentMonth = currentMonth
                    currentDay = dayOfMonth
                }, currentYear, currentMonth, currentDay
            ).show()
        }
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return MineConstants.auth_ways[2]
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
