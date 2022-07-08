package com.example.foodapp.data.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.FoodJoke
import com.example.foodapp.util.Constants.FOOD_JOKE_TABLE

@Entity(tableName = FOOD_JOKE_TABLE)
data class FoodJokeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @Embedded
    val foodJoke: FoodJoke
)
