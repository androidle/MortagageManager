package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthOrganizationBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.domain.MineConstants
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment_auth_organization.*

class OrganizationAuthFragment : BaseFragment() {

    private var viewBinding by autoCleared<MineFragmentAuthOrganizationBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
            viewModel = this@OrganizationAuthFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_identity_type.setOnClickListener {
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
        }
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
