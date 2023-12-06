package com.kake.base.util

import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi

// androidMain/src/androidMain/kotlin/MyImageDecoder.kt
@RequiresApi(Build.VERSION_CODES.FROYO)
actual fun String.decodeThumbnailImage(): Image? {
    return try {
        val byteArray = android.util.Base64.decode(this, android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(
            byteArray,
            0,
            byteArray.size
        )
        if (bitmap != null) Image(bitmap) else null
    } catch (e: Exception) {
        null
    }
}