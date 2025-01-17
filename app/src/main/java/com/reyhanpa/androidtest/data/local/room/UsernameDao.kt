package com.reyhanpa.androidtest.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyhanpa.androidtest.data.local.entity.DataEntity

@Dao
interface UsernameDao {
    @Query("SELECT * FROM username")
    fun getAllUsernames(): PagingSource<Int, DataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsernames(events: List<DataEntity>)

    @Query("DELETE FROM username")
    suspend fun deleteAll()
}