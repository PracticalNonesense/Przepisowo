package com.example.cookbook_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData

class RecipeViewModel(private val recipeDao: RecipeDao) : ViewModel() {

    val allRecipes: LiveData<List<RecipeEntity>> = recipeDao.getAllRecipesLive()

    fun insertSampleRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeCount = recipeDao.getRecipeCount()
            if (recipeCount == 0) {
                val sampleRecipes = listOf(
                    RecipeEntity(
                        name = "Pizza neapolitańska",
                        description = "Przykładowy opis przepisu.",
                        ingredients = "mąka włoska, woda, drożdże, sól, oliwa, pomidory, mozzarella",
                        instructions = "1. Wymieszaj składniki...\n2. Uformuj ciasto...",
                        image = "res/drawable/pizza_nea"
                    ),
                    RecipeEntity(
                        name = "Lasagna",
                        description = "Tradycyjna włoska potrawa z makaronem i sosem mięsnym.",
                        ingredients = "makaron lasagna, mięso mielone, sos pomidorowy, ser",
                        instructions = "1. Przygotuj sos...\n2. Układaj warstwy makaronu...",
                        image = "res/drawable/lasagna"
                    ),
                    RecipeEntity(
                        name = "Spaghetti Carbonara",
                        description = "Tradycyjna włoska potrawa z makaronem i sosem serowym z żółtkiem jajka.",
                        ingredients = "makaron spaghetti, jajka, pancetta, pecorino romano, sól, pieprz" ,
                        instructions = "1. Przygotuj sos...\n2. Układaj warstwy makaronu...",
                        image = "res/drawable/carbonara"
                    )


                )

                // Użycie nowej metody do wstawienia listy przepisów
                recipeDao.insertRecipes(sampleRecipes)
            }
        }
    }
}
