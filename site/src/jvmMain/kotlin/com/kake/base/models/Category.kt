package com.kake.base.models

import kotlinx.serialization.Serializable

@Serializable
enum class Category(val color: String) {
    Technology(color = Theme.Green.hex), Programming(color = Theme.Yellow.hex), Design(color = Theme.Purple.hex),
}