package com.leevinapp.monitor.mine.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class ApplyParentInstitutionFragment : ApplyInstitutionFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userManager: UserManager

    val viewModel: ApplyParentInstitutionViewModel by viewModels {
        viewModelFactory
    }

    override fun getApplyViewModel(): ApplyParentInstitutionViewModel {
        return viewModel
    }

    override fun userManager(): UserManager {
        return userManager
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_apply_parent_institution)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
