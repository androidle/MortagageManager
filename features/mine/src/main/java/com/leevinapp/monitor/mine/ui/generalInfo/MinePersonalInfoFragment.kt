package com.leevinapp.monitor.mine.ui.generalInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentPersonalInfoBinding
import com.leevinapp.monitor.mine.di.buildComponent
import kotlinx.android.synthetic.main.mine_fragment_personal_info.*
import javax.inject.Inject

class MinePersonalInfoFragment : ViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: PersonalInfoViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var viewBinding: MineFragmentPersonalInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentPersonalInfoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MinePersonalInfoFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel.getUserProfile(userManager.user.userId)

        iv_mail.setOnClickListener {
            // verify email
            findNavController().navigate(R.id.mine_action_mine_minepersonalinfofragment_to_verifyemailfragment)
        }
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_personal_info)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun isShowRightActionText(): Boolean {
        return true
    }

    override fun getRightText(): String {
        return getString(R.string.mine_completed)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
