package com.leevinapp.monitor.mine.ui.identityauth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.mine.domain.MineRepository
import javax.inject.Inject

class IdentityAuthViewModel @Inject constructor(private val repository: MineRepository) :
    ViewModel() {
    val realName = MutableLiveData("")
    val identityType = MutableLiveData("")
    val identityNum = MutableLiveData("")
    val homeAddress = MutableLiveData("")
    val companyName = MutableLiveData("")
    val jobPosition = MutableLiveData("")
    val monitorOrgan = MutableLiveData("")
    val monitorOrganSocialCode = MutableLiveData("")
    val socialCode = MutableLiveData("")
    val companyPresenter = MutableLiveData("")
    val registeredCapital = MutableLiveData("")
    val dateOfFounded = MutableLiveData("")
    val operatingPeriod = MutableLiveData("")
    val businessScope = MutableLiveData("")
    val organAdmin = MutableLiveData("")
}
