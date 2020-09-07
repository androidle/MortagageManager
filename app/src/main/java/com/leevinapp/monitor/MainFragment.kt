package com.leevinapp.monitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.setupWithNavController
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.databinding.FragmentMainBinding
import com.leevinapp.monitor.di.DaggerMainComponent
import com.leevinapp.monitor.di.MainModule
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewBinding:FragmentMainBinding

    val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MainFragment.viewModel
            viewBinding = this
        }.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home, R.navigation.project,
            R.navigation.enterprise, R.navigation.mine_graph
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = viewBinding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(viewLifecycleOwner, Observer { navController ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (NAV_FRAGMENTS_ID.contains(destination.id)) {
                    viewBinding.bottomNav.visibility = View.VISIBLE
                } else {
                    viewBinding.bottomNav.visibility = View.GONE
                }
            }
        })
    }

    override fun initDependencyInjection() {
        DaggerMainComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .mainModule(MainModule())
            .build()
            .inject(this)
    }
}
