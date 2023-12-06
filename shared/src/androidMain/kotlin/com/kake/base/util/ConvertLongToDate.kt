package com.kake.base.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual fun Long.convertLongToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    )
    return format.format(date)
}