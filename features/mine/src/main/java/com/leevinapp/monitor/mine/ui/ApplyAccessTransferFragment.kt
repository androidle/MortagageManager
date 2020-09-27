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
import com.leevinapp.monitor.mine.databinding.MineFragmentApplyAccessTransferBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.ui.adapter.SearchUserAdapter
import javax.inject.Inject

class ApplyAccessTransferFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentApplyAccessTransferBinding>()

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModel: ApplyAccessTransferViewModel

    private lateinit var searchResultAdapter: SearchUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentApplyAccessTransferBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ApplyAccessTransferFragment.viewModel
            userManager = this@ApplyAccessTransferFragment.userManager
            viewBinding = this
        }.root
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchInputListener()

        viewBinding.buttonSubmit.setOnClickListener {
            viewModel.requestTicket()
        }

        searchResultAdapter = SearchUserAdapter()

        viewModel.userListResult.observe(viewLifecycleOwner, Observer {
            viewBinding.searchResultContainer.visibility = View.VISIBLE
            viewBinding.recyclerViewSearchResult.adapter = searchResultAdapter.apply {
                viewBinding.searchResultTitle.text =
                    getString(R.string.mine_search_result_title, it.size)
                updateResult(it)
                setItemClick {
                    clearAndHideResultContainer()
                    viewModel.targetUser(it)
                }
            }
        })

        viewModel.requestTicketResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "已提交申请，等待管理员审核", Toast.LENGTH_LONG).show()
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
        viewModel.setQuery(query)
        viewModel.getSearchUser()
    }

    private fun clearAndHideResultContainer() {
        searchResultAdapter.clear()
        viewBinding.searchResultContainer.visibility = View.GONE
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_access_transfer)
    }
}
