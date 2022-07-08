package com.example.foodapp.data.database.daos

import androidx.room.*
import com.example.foodapp.data.database.models.FavoriteEntity
import com.example.foodapp.data.database.models.FoodJokeEntity
import com.example.foodapp.data.database.models.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    // RECIPES TABLE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>

    // FAVORITE RECIPES TABLE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites_recipe_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorites_recipe_table")
    suspend fun deleteAllFavoriteRecipes()

    // FOOD JOKE TABLE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("SELECT * FROM food_joke_table")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>
}