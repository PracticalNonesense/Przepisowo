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
        val database = AppDatabase.getDatabase(this) // Poprawiona inicjalizacja bazy danych
        val recipeDao = database.recipeDao()
        val viewModelFactory = RecipeViewModelFactory(recipeDao)
        recipeViewModel = ViewModelProvider(this, viewModelFactory).get(RecipeViewModel::class.java)

        // Wstawienie przykładowego przepisu
        recipeViewModel.insertSampleRecipe()

        // Obsługa dopasowania układu do systemowych pasków (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Wyszukiwanie przepisów
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.queryHint = "Wyszukaj przepis"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Obsługuje wyszukiwanie po wciśnięciu klawisza "enter"
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Obsługuje zmiany w treści wyszukiwania
                return false
            }
        })

        // RecyclerView
        recyclerView = findViewById(R.id.placeholder_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

// Inicjalizacja adaptera z kontekstem i pustą listą
        recipeAdapter = RecipeAdapter(this, emptyList()) // Przekazanie kontekstu oraz pustej listy
        recyclerView.adapter = recipeAdapter

// Obserwacja na zmiany w bazie danych
        recipeViewModel.allRecipes.observe(this, { recipes ->
            recipeAdapter = RecipeAdapter(this, recipes) // Przekazanie kontekstu oraz listy przepisów
            recyclerView.adapter = recipeAdapter
        })

    }

    // Funkcja do przejścia do MainActivity
    fun goToMainActivity(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
