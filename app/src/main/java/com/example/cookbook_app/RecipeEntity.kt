package com.example.cookbook_app
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: String,
    val instructions: String,
    val image: String,
    val type: String
)