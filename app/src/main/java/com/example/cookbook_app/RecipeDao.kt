package com.example.cookbook_app

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {
    // Zmieniamy metodę tak, aby przyjmowała listę przepisów
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipes")
    fun getAllRecipesLive(): LiveData<List<RecipeEntity>>

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("SELECT COUNT(*) FROM recipes")
    fun getRecipeCount(): Int
}
