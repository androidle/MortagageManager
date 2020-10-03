package com.leevinapp.monitor.core.common.ui.dialog

import android.os.Bundle
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.databinding.FragmentDialogSearchBinding

class SearchDialogFragment : BaseDialogFragment<FragmentDialogSearchBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_dialog_search
    }
}
