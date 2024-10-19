package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.models.FavoriteEntity
import com.example.local.models.SearchItemEntity

@Dao
interface GlobaStyleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorites(item: FavoriteEntity)

    @Query("select * from FavoriteEntity")
    fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecentSearch(item: SearchItemEntity)

    @Query("select * from SearchItemEntity")
    fun getRecentSearches(): List<SearchItemEntity>

    @Query("delete from SearchItemEntity where id = :itemId")
    fun deleteFavorite(itemId: String)

    @Query("delete from SearchItemEntity")
    fun deleteAllRecentSearches()
}