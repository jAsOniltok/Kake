package com.kake.base.util

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.dateWithTimeIntervalSince1970

actual fun Long.convertLongToDate(): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
    val date = NSDate.dateWithTimeIntervalSince1970(this.toDouble())
    return dateFormatter.stringFromDate(date)
}