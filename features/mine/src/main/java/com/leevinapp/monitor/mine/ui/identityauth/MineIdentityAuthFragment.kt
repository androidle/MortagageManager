package com.leevinapp.monitor.mine.ui.identityauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import kotlinx.android.synthetic.main.mine_fragment_auth.*

class MineIdentityAuthFragment : BaseFragment() {

    private val args: MineIdentityAuthFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_fragment_auth, container, false)
    }

    override fun getToolbar(): View? {
        return toolbar_container
    }

    override fun getToolbarTitle(): String {
        return args.authModel.name
    }
}
