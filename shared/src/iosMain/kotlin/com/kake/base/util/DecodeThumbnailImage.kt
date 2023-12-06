package com.kake.base.util

import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSData
import platform.Foundation.NSDataBase64DecodingOptions
import platform.Foundation.create
import platform.UIKit.UIImage

@OptIn(BetaInteropApi::class)
actual fun String.decodeThumbnailImage(): Image? {
    val data = NSData.create(base64EncodedString = this, options = NSDataBase64DecodingOptions.MIN_VALUE)
    val uiImage = data?.let { UIImage.imageWithData(it) }
    return uiImage?.let { Image(it) }
}