package com.example.cookbook_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Categories : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories)

        // Inicjalizacja bazy danych i ViewModel
        val database = AppDatabase.getDatabase(this)
        val recipeDao = database.recipeDao()
        val viewModelFactory = RecipeViewModelFactory(recipeDao)
        recipeViewModel = ViewModelProvider(this, viewModelFactory).get(RecipeViewModel::class.java)

        // Wstawienie przykładowych przepisów
        recipeViewModel.insertSampleRecipes()

        // Obsługa układu Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Wyszukiwanie
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.queryHint = "Wyszukaj przepis"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // RecyclerView i Adapter
        recyclerView = findViewById(R.id.placeholder_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(this) // Teraz konstruktor wymaga tylko kontekstu
        recyclerView.adapter = recipeAdapter

        // Obserwacja danych
        recipeViewModel.allRecipes.observe(this) { recipes ->
            if (recipes.isNotEmpty()) {
                println("Loaded ${recipes.size} recipes from the database.")
                recipes.forEach { println("Recipe: ${it.name}, Ingredients: ${it.ingredients}") }
            } else {
                println("No recipes found in the database.")
            }
        }


    }

    // Funkcja do przejścia do MainActivity
    fun goToMainActivity(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

