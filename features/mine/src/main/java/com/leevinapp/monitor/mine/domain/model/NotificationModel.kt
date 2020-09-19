package com.leevinapp.monitor.mine.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NotificationModel(
    var title:String = "",
    var isRead:Boolean = false,
    var date:String = "",
    var applicant:String = "",
    var phoneNumber:String = "",
    var status:String = ""
) : Parcelable