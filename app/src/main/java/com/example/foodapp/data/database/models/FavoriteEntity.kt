package com.example.foodapp.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.Result
import com.example.foodapp.util.Constants.FAVORITES_RECIPES_TABLE

@Entity(tableName = FAVORITES_RECIPES_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)