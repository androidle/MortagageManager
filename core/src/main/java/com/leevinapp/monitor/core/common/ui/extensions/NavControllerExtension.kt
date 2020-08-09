package com.leevinapp.monitor.core.common.ui.extensions

import android.net.Uri
import androidx.navigation.NavController

fun NavController.navigationToLogonFragment() {
    // todo is need to be a single activity for logon flow
    navigate(Uri.parse("Monitor://LogonFragment"))
}
