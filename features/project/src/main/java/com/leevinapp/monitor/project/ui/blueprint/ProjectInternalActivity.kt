package com.leevinapp.monitor.project.ui.blueprint

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.leevinapp.monitor.core.common.ui.base.BaseActivity
import com.leevinapp.monitor.project.R

class ProjectInternalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_activity_internal)
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .apply {
                if (addToBackStack) {
                    addToBackStack(fragment.javaClass.simpleName)
                }
            }
            .commit()
    }
}
