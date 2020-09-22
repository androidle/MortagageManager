package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.ui.extensions.hideKeyBoard
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentApplyParentOrganBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.ui.adapter.SearchResultAdapter
import javax.inject.Inject

class ApplyParentInstitutionFragment : ViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ApplyParentInstitutionViewModel by viewModels {
        viewModelFactory
    }

    private var viewBinding by autoCleared<MineFragmentApplyParentOrganBinding>()

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentApplyParentOrganBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ApplyParentInstitutionFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchInputListener()

        viewModel.result.observe(viewLifecycleOwner, Observer {
            viewBinding.searchResultContainer.visibility = View.VISIBLE
            viewBinding.recyclerViewSearchResult.adapter = SearchResultAdapter().apply {
                updateResult(it)
            }
        })
    }

    private fun initSearchInputListener() {
        viewBinding.searchInput.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        viewBinding.searchInput.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(view: View) {
        val query = viewBinding.searchInput.text.toString()
        // Dismiss keyboard
        view.hideKeyBoard()
        viewModel.setQuery(query)
        viewModel.getParentInstitution()
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_apply_parent_institution)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
