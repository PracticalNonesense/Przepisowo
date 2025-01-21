package com.example.cookbook_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private val context: Context, private val recipes: List<RecipeEntity>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_item_placeholder, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        // Załaduj obrazek z zasobów (imagePath)
        val imageResId = context.resources.getIdentifier(recipe.image, "drawable", context.packageName)

        // Używamy Glide do załadowania obrazu (jeśli dostępny)
        if (imageResId != 0) {
            Glide.with(context)
                .load(imageResId)
                .into(holder.recipeImage)
        } else {
            // Jeśli brak obrazu, ustaw pusty placeholder lub inny obraz
            holder.recipeImage.setImageResource(R.drawable.pizza_nea)
        }

        // Ustawienie nazwy przepisu
        holder.recipeName.text = recipe.name
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    // ViewHolder dla pojedynczego przepisu
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        val recipeName: TextView = itemView.findViewById(R.id.recipe_name)
    }
}
