package com.reyhanpa.androidtest.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reyhanpa.androidtest.data.local.entity.DataEntity
import com.reyhanpa.androidtest.data.local.entity.RemoteKeys

@Database(
    entities = [DataEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class UsernameDatabase : RoomDatabase() {
    abstract fun usernameDao(): UsernameDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: UsernameDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UsernameDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UsernameDatabase::class.java, "Username_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}