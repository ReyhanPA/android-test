package com.reyhanpa.androidtest.utils

import com.reyhanpa.androidtest.data.local.entity.DataEntity
import com.reyhanpa.androidtest.data.remote.response.DataItem

fun DataEntity.toDataItem(): DataItem {
    return DataItem(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        avatar = this.avatar,
    )
}