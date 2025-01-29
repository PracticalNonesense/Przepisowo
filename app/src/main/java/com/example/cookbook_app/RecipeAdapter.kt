package com.example.cookbook_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private val context: Context) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    // Ręczne listy danych dla przepisów
    private val recipeNames = listOf(
        "Pizza neapolitańska",
        "Lasagna",
        "Spaghetti Carbonara",
        "Sernik z budyniem",
        "Zupa ogórkowa na wędzonce",
        "Koreczki z fetą",
        "Wegańskie burgery",
        "Złota lemoniada herbaciana"
    )

    private val recipeIngredients = listOf(
        "mąka włoska, woda, drożdże, sól, oliwa, pomidory, mozzarella", // Pizza
        "makaron lasagna, mięso mielone, sos pomidorowy, ser",          // Lasagna
        "makaron spaghetti, jajka, pancetta, pecorino romano, sól, pieprz", // Carbonara
        "ser twarogowy 1 kilogram, budyń waniliowy 3 łyżki, cukier 180 gramów, jajka 3 sztuki, śmietanka 30% 100 gramów.",
        "kiszone ogórki 300 gramów, wędzonka lub kości wędzone 200 gramów, marchewka 1 sztuka, ziemniaki 5 sztuk, koperek 1 pęczek, liść laurowy 2 sztuki, ziele angielskie 2 ziarna, woda 2 litry",
        "ser feta 2 opakowania, oliwki 1 słoik, ogórki 1 sztuka, salami 1 opakowanie, pomidorki koktajlowe 200 gramów, winogrona 200 gramów",
        "ryż 125 gramów, czarna fasola 125 gramów, cebula 100 gramów, kmin rzymski 1 sztuka, papryka słodka 1 łyżka, papryka ostra w proszku mielona 1 łyżeczka, orzechy włoskie 125 gramów, panierka panko 25 gramów",
        "Herbata Lipton Yellow Label Tea 1 sztuka, cytryna 2 plastry, brzoskwinia 0.5 sztuki, cukier 3 łyżeczki, gałązka mięty 1 sztukax1"
    )

    private val recipeInstructions = listOf(
        "1. Wymieszaj składniki.\n2. Uformuj ciasto.\n3. Piecz w piekarniku.", // Pizza
        "1. Przygotuj sos.\n2. Układaj warstwy makaronu.\n3. Piecz w piekarniku.", // Lasagna
        "1. Podsmaż pancettę.\n2. Ugotuj makaron.\n3. Wymieszaj wszystko z sosem.", // Carbonara
        "Placeholder instrukcje 1",
        "Placeholder instrukcje 2",
        "Placeholder instrukcje 3",
        "Placeholder instrukcje 4",
        "Placeholder instrukcje 5"
    )
    private val recipeCategories = listOf(
        "dania główne",    // Pizza
        "dania główne",    // Lasagna
        "dania główne",    // Spaghetti Carbonara
        "desery",          // Sernik z budyniem
        "zupy",            // Zupa ogórkowa
        "przekąski",       // Koreczki z fetą
        "dania wegańskie", // Wegańskie burgery
        "napoje"           // Złota lemoniada herbaciana
    )

    private val imageNames = listOf(
        "pizza_nea",   // Obraz dla pizzy
        "lasagna",     // Obraz dla lasagny
        "carbonara",   // Obraz dla carbonary
        "sernik", // Placeholder 1
        "ogor", // Placeholder 2
        "koreczki", // Placeholder 3
        "burger", // Placeholder 4
        "lemoniada"  // Placeholder 5
    )

    private var filteredRecipeNames = recipeNames.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_item_placeholder, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        // Ustaw dane dla pozycji w RecyclerView
        holder.recipeName?.text = filteredRecipeNames[position]
        holder.recipeIngredients?.text = recipeIngredients[recipeNames.indexOf(filteredRecipeNames[position])]

        val imageName = imageNames[recipeNames.indexOf(filteredRecipeNames[position])]
        val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

        holder.recipeImage?.let { imageView ->
            if (imageResId != 0) {
                // Załaduj obraz za pomocą Glide
                Glide.with(context)
                    .load(imageResId)
                    .placeholder(R.drawable.placeholder_image) // Placeholder, jeśli obraz jest ładowany
                    .error(R.drawable.placeholder_image)       // Placeholder, jeśli obraz nie zostanie znaleziony
                    .into(imageView)
            } else {
                // Jeśli obraz nie istnieje w zasobach, ustaw obraz zastępczy
                imageView.setImageResource(R.drawable.placeholder_image)
            }
        }
    }

    override fun getItemCount(): Int = filteredRecipeNames.size

    fun filterByCategory(category: String) {
        filteredRecipeNames = if (category.isEmpty()) {
            recipeNames.toMutableList() // Pokazuj wszystkie przepisy, jeśli brak kategorii
        } else {
            // Filtruj przepisy według kategorii
            recipeNames.filterIndexed { index, _ ->
                recipeCategories[index].equals(category, ignoreCase = true)
            }.toMutableList()
        }
        println("Filtered recipes: $filteredRecipeNames") // Loguj wyniki
        notifyDataSetChanged() // Odśwież listę
    }


    fun filter(query: String) {
        filteredRecipeNames = if (query.isEmpty()) {
            recipeNames.toMutableList()
        } else {
            recipeNames.filter { it.contains(query, ignoreCase = true) }.toMutableList()
        }
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView? = itemView.findViewById(R.id.image)
        val recipeName: TextView? = itemView.findViewById(R.id.recipe_name)
        val recipeIngredients: TextView? = itemView.findViewById(R.id.recipe_ingredients)
    }
}



