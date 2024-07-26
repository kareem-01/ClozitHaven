package com.example.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val name: String,
    val price: String,
)
