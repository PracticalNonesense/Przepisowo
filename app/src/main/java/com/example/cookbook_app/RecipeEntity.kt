package com.example.cookbook_app
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int, // Unikalny identyfikator
    val name: String, // Nazwa przepisu
    val description: String, // Opis przepisu
    val ingredients: String, // Składniki w formacie tekstowym
    val instructions: String, // Instrukcje przygotowania
    val image: String // URI obrazu (opcjonalne)
)