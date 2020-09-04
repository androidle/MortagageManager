package com.leevinapp.monitor.mine.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OptionModel(
    var id: String = "",
    var name: String = ""
) : Parcelable
