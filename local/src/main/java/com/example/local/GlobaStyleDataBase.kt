package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.dao.GlobaStyleDao
import com.example.local.models.FavoriteEntity
import com.example.local.models.SearchItemEntity

@Database(version = 1, entities = [FavoriteEntity::class, SearchItemEntity::class])
abstract class GlobaStyleDataBase : RoomDatabase() {
    abstract val globaStyleDao: GlobaStyleDao
}