package debug

import android.os.Bundle
import com.leevinapp.monitor.core.common.ui.base.BaseActivity
import com.leevinapp.monitor.enterprise.R

class EnterpriseDebugActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enterprise_activity_debug)
    }
}
