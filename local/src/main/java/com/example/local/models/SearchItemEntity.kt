package com.example.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchItemEntity(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val name: String,
    val price: String,
    val rating: Double,
)
