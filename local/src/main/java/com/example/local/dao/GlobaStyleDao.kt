package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.models.FavoriteEntity

@Dao
interface GlobaStyleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorites(item: FavoriteEntity)

    @Query("select * from FavoriteEntity")
    fun getFavorites(): List<FavoriteEntity>

}