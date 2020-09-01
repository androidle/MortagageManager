package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.navigationToLogonFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.R.layout
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_fragment.*

class MineFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: MineViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // TODO: 2020/8/31 distinct pre-logon and post-logon
        val view = inflater.inflate(layout.mine_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_unlogon.setOnClickListener {
            // todo go to logon page
            findNavController().navigationToLogonFragment()
        }

        if (userManager.isLogged) {
            container_post_logon.visibility = View.VISIBLE
            container_unlogon.visibility = View.GONE
        } else {
            container_post_logon.visibility = View.GONE
            container_unlogon.visibility = View.VISIBLE
        }
    }

    override fun initDependencyInjection() {
    }
}
