package com.leevinapp.monitor.core.core.utils

import com.leevinapp.monitor.core.core.config.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toString(
    pattern: String = Constants.DATE_FORMAT_UTC,
    local: Locale = Locale.getDefault()
): String = SimpleDateFormat(pattern, local).format(this)

fun Date.toYearMonthDay(): String {
    return this.toString(Constants.DATE_FORMAT_YEAR_MONTH_DAY)
}

fun Date.removeTime(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}
