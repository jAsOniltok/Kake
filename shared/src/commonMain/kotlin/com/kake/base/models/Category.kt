package com.kake.base.models

import com.kake.base.CategoryCommon

enum class Category(override val color: String): CategoryCommon {
    Programming(color = ""),
    Technology(color = ""),
    Design(color = "")
}