package com.leevinapp.monitor.mine.domain.model

import android.os.Parcelable
import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.core.core.utils.toYearMonthDay
import java.util.Calendar
import java.util.Date
import kotlinx.android.parcel.Parcelize

@Parcelize
class NotificationModel(
    var id: Long = 0,
    var isRead: Boolean = false,
    var isApproved: Boolean = false,
    var notifyDate: Date = Calendar.getInstance().time,
    var applicant: String = "",
    var phoneNumber: String = "",
    var organName: String = "",
    var homeAddress: String = "",
    var identityNumber: String = "",
    var role: UserRole,
    var jobPosition: String = "",
    var socialCode: String = "",
    var comment: String = "",
    var type: TicketType
) : Parcelable {
    fun toDateShow(): String {
        return notifyDate.toYearMonthDay()
    }
}
