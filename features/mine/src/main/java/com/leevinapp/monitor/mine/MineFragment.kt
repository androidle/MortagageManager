package com.leevinapp.monitor.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.extensions.navigationToLogonFragment
import kotlinx.android.synthetic.main.mine_fragment.*

/**
 * Shows a register form to showcase UI state persistence. It has a button that goes to [Registered]
 */
class MineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.mine_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_unlogon.setOnClickListener {
            // todo go to logon page
            findNavController().navigationToLogonFragment()
        }
    }
}
