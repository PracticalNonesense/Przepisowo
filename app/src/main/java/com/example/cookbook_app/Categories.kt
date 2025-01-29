package com.example.cookbook_app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Categories : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView

    private val CAMERA_REQUEST_CODE = 100
    private val CAMERA_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        // Inicjalizacja RecyclerView i Adaptera
        recyclerView = findViewById(R.id.placeholder_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(this)
        recyclerView.adapter = recipeAdapter

        // Filtracja początkowa - pokazuje wszystkie przepisy
        recipeAdapter.filterByCategory("")

        // Obsługa przycisków kategorii
        setupCategoryButtons()

        // Obsługa wyszukiwarki
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeAdapter.filter(newText ?: "")
                return true
            }
        })

        // Obsługa kliknięcia na ikonkę kamery
        val cameraIcon = findViewById<ImageView>(R.id.camera_icon)
        cameraIcon.setOnClickListener {
            if (checkAndRequestPermissions()) {
                openCamera()
            }
        }
    }

    private fun setupCategoryButtons() {
        // Przyciski kategorii
        findViewById<Button>(R.id.button_category1).setOnClickListener {
            recipeAdapter.filterByCategory("desery") // Przypisz odpowiednią kategorię
        }
        findViewById<Button>(R.id.button_category2).setOnClickListener {
            recipeAdapter.filterByCategory("zupy")
        }
        findViewById<Button>(R.id.button_category3).setOnClickListener {
            recipeAdapter.filterByCategory("dania główne")
        }
        findViewById<Button>(R.id.button_category4).setOnClickListener {
            recipeAdapter.filterByCategory("przekąski")
        }
        findViewById<Button>(R.id.button_category5).setOnClickListener {
            recipeAdapter.filterByCategory("napoje")
        }
        findViewById<Button>(R.id.button_category6).setOnClickListener {
            recipeAdapter.filterByCategory("dania wegańskie")
        }
    }

    // Sprawdzanie i żądanie uprawnień do kamery
    private fun checkAndRequestPermissions(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                CAMERA_PERMISSION_CODE
            )
            false
        } else {
            true
        }
    }

    // Uruchamianie aparatu
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Brak aplikacji do obsługi aparatu", Toast.LENGTH_SHORT).show()
        }
    }

    // Obsługa wyników żądania uprawnień
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Uprawnienia przyznane. Uruchamiam aparat...", Toast.LENGTH_SHORT).show()
                openCamera()
            } else {
                Toast.makeText(this, "Brak uprawnień do uruchomienia aparatu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun goToMainActivity(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Opcjonalnie zamyka obecną aktywność, by użytkownik nie wracał do niej przyciskiem "Wstecz"
    }
    // Obsługa wyniku działania aparatu
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            Toast.makeText(this, "Zdjęcie zapisane: $imageUri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Nie wykonano zdjęcia", Toast.LENGTH_SHORT).show()
        }
    }
}
