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
        "Placeholder Przepis 1",
        "Placeholder Przepis 2",
        "Placeholder Przepis 3",
        "Placeholder Przepis 4",
        "Placeholder Przepis 5"
    )

    private val recipeIngredients = listOf(
        "mąka włoska, woda, drożdże, sól, oliwa, pomidory, mozzarella", // Pizza
        "makaron lasagna, mięso mielone, sos pomidorowy, ser",          // Lasagna
        "makaron spaghetti, jajka, pancetta, pecorino romano, sól, pieprz", // Carbonara
        "Placeholder składniki 1",
        "Placeholder składniki 2",
        "Placeholder składniki 3",
        "Placeholder składniki 4",
        "Placeholder składniki 5"
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

    private val imageNames = listOf(
        "pizza_nea",   // Obraz dla pizzy
        "lasagna",     // Obraz dla lasagny
        "carbonara",   // Obraz dla carbonary
        "placeholder_image", // Placeholder 1
        "placeholder_image", // Placeholder 2
        "placeholder_image", // Placeholder 3
        "placeholder_image", // Placeholder 4
        "placeholder_image"  // Placeholder 5
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_item_placeholder, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        // Ustawienie danych dla pozycji w RecyclerView
        holder.recipeName?.text = recipeNames[position]
        holder.recipeIngredients?.text = recipeIngredients[position]

        // Obsługa obrazu
        val imageName = imageNames[position]
        val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

        holder.recipeImage?.let { imageView ->
            if (imageResId != 0) {
                Glide.with(context)
                    .load(imageResId)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.placeholder_image)
            }
        }
    }

    override fun getItemCount(): Int = recipeNames.size // Zwraca liczbę przepisów

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView? = itemView.findViewById(R.id.image)
        val recipeName: TextView? = itemView.findViewById(R.id.recipe_name)
        val recipeIngredients: TextView? = itemView.findViewById(R.id.recipe_ingredients)
    }
}



