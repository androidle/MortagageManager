package com.leevinapp.monitor

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.leevinapp.monitor.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openVideo(view: View) {
        startActivity(Intent(this, VideoPlayerActivity::class.java))
    }

    fun gotoHomeActivity(view: View) {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}
