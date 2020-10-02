package com.leevinapp.monitor.mine.ui.generalInfo

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
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentPersonalInfoBinding
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class PersonalInfoFragment : ViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userManager: UserManager

    val viewModel: PersonalInfoViewModel by viewModels {
        viewModelFactory
    }

    private var viewBinding by autoCleared<MineFragmentPersonalInfoBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentPersonalInfoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@PersonalInfoFragment.viewModel
            userManager = this@PersonalInfoFragment.userManager
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateProfileResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), if (it) "更新成功" else "更新失败", Toast.LENGTH_LONG)
                    .show()
                findNavController().navigateUp()
            }
        })

        viewModel.getUserProfile()
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

    override fun onRightTextClick() {
        viewModel.updateUserProfile()
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
