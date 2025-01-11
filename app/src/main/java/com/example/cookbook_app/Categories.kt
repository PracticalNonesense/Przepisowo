package com.example.cookbook_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories)

        // Obsługa dopasowania układu do systemowych pasków (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchView = findViewById<SearchView>(R.id.search_view)

// Ustawienie tekstu pomocniczego (hint)
        searchView.queryHint = "Wyszukaj przepis"

// Ustawienie koloru tekstu pomocniczego (hint)
        searchView.setQueryHint("Wyszukaj przepis")

// Ustaw listener na kliknięcie na cały SearchView
        searchView.setOnClickListener {
            if (!searchView.isIconified) {
                // Jeśli SearchView jest już otwarte (rozwinęte), wykonaj wyszukiwanie
                searchView.setIconified(false)
            } else {
                // Jeśli SearchView jest skompresowane, to go rozwijamy
                searchView.setIconified(true)
            }
        }

// Listener na zmiany tekstu w wyszukiwarce
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

        fun goToMainActivity(view: android.view.View) {
            // Przechodzenie do MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
