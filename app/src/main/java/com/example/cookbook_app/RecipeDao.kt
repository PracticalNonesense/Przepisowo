    package com.example.cookbook_app

    import androidx.lifecycle.LiveData
    import androidx.room.*

    @Dao
    interface RecipeDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertRecipe(recipe: RecipeEntity)

        @Query("SELECT * FROM recipes")
        fun getAllRecipesLive(): LiveData<List<RecipeEntity>>

        @Delete
        suspend fun deleteRecipe(recipe: RecipeEntity)
    }
