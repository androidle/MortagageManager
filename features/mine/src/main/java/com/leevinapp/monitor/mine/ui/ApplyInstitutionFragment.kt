package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.ui.extensions.hideKeyBoard
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentApplyInstitutionBinding
import com.leevinapp.monitor.mine.ui.adapter.SearchResultAdapter

abstract class ApplyInstitutionFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentApplyInstitutionBinding>()

    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun getViewModel(): BaseViewModel {
        return getApplyViewModel()
    }

    abstract fun getApplyViewModel(): ApplyParentInstitutionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentApplyInstitutionBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ApplyInstitutionFragment.getApplyViewModel()
            userManager = this@ApplyInstitutionFragment.userManager()
            viewBinding = this
        }.root
    }

    abstract fun userManager(): UserManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchInputListener()

        viewBinding.buttonSubmit.setOnClickListener {
            getApplyViewModel().requestTicket()
        }

        searchResultAdapter = SearchResultAdapter()

        getApplyViewModel().result.observe(viewLifecycleOwner, Observer {
            viewBinding.searchResultContainer.visibility = View.VISIBLE
            viewBinding.recyclerViewSearchResult.adapter = searchResultAdapter.apply {
                viewBinding.searchResultTitle.text =
                    getString(R.string.mine_search_result_title, it.size)
                updateResult(it)
                setItemClick {
                    clearAndHideResultContainer()
                    getApplyViewModel().targetInstitution(it)
                }
            }
        })

        getApplyViewModel().requestTicketResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "已提交申请，等待管理员审核", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun clearAndHideResultContainer() {
        searchResultAdapter.clear()
        viewBinding.searchResultContainer.visibility = View.GONE
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

        viewBinding.searchInput.addTextChangedListener(afterTextChanged = {
            val searchKey = it.toString()
            if (searchKey.isEmpty()) {
                clearAndHideResultContainer()
            } else {
                viewBinding.recyclerViewSearchResult.visibility = View.VISIBLE
            }
        })
    }

    private fun doSearch(view: View) {
        val query = viewBinding.searchInput.text.toString()
        // Dismiss keyboard
        view.hideKeyBoard()
        getApplyViewModel().setQuery(query)
        getApplyViewModel().searchInstitution()
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }
}
