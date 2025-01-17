package com.reyhanpa.androidtest.utils

import com.reyhanpa.androidtest.data.local.entity.DataEntity
import com.reyhanpa.androidtest.data.remote.response.DataItem

fun DataItem.toEntity(): DataEntity {
    return DataEntity(
        id = this.id ?: 0,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        avatar = this.avatar,
    )
}

fun List<DataItem>.toEntityList(): List<DataEntity> {
    return this.map { it.toEntity() }
}