package com.example.cookbook_app

import android.app.Application

class CookbookApplication : Application() {

    // Lazy inicjalizacja bazy danych i DAO
    val database by lazy { AppDatabase.getDatabase(this) }
    val recipeDao by lazy { database.recipeDao() }
}
