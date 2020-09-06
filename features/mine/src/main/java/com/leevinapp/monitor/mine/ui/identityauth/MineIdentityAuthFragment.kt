package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.databinding.MineFragmentAuthBinding

class MineIdentityAuthFragment : BaseFragment() {

    private val args: MineIdentityAuthFragmentArgs by navArgs()
    private lateinit var viewBinding: MineFragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentAuthBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun getToolbar(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getToolbarTitle(): String {
        return args.authModel.name
    }
}
