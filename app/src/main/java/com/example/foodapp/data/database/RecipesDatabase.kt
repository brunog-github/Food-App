package com.example.foodapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.data.database.daos.RecipesDao
import com.example.foodapp.data.database.models.FavoriteEntity
import com.example.foodapp.data.database.models.FoodJokeEntity
import com.example.foodapp.data.database.models.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoriteEntity::class, FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao
}