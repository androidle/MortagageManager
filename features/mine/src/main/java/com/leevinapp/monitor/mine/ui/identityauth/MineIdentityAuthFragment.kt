package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthBinding
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class MineIdentityAuthFragment : BaseFragment() {

    private val args: MineIdentityAuthFragmentArgs by navArgs()

    private lateinit var viewBinding: MineFragmentAuthBinding

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
        return MineFragmentAuthBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MineIdentityAuthFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return args.authModel.name
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
