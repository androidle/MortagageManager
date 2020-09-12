package com.leevinapp.monitor.mine.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OptionModel(
    var id: Int = 0,
    var name: String = ""
) : Parcelable
