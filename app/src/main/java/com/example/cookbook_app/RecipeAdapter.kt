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
        "mąka włoska, woda, drożdże, sól, oliwa, pomidory, mozzarella",
        "makaron lasagna, mięso mielone, sos pomidorowy, ser",
        "makaron spaghetti, jajka, pancetta, pecorino romano, sól, pieprz",
        "ser twarogowy 1 kilogram, budyń waniliowy 3 łyżki, cukier 180 gramów, jajka 3 sztuki, śmietanka 30% 100 gramów.",
        "kiszone ogórki 300 gramów, wędzonka lub kości wędzone 200 gramów, marchewka 1 sztuka, ziemniaki 5 sztuk, koperek 1 pęczek, liść laurowy 2 sztuki, ziele angielskie 2 ziarna, woda 2 litry",
        "ser feta 2 opakowania, oliwki 1 słoik, ogórki 1 sztuka, salami 1 opakowanie, pomidorki koktajlowe 200 gramów, winogrona 200 gramów",
        "ryż 125 gramów, czarna fasola 125 gramów, cebula 100 gramów, kmin rzymski 1 sztuka, papryka słodka 1 łyżka, papryka ostra w proszku mielona 1 łyżeczka, orzechy włoskie 125 gramów, panierka panko 25 gramów",
        "Herbata Lipton Yellow Label Tea 1 sztuka, cytryna 2 plastry, brzoskwinia 0.5 sztuki, cukier 3 łyżeczki, gałązka mięty 1 sztuka"
    )

    private val recipeCategories = listOf(
        "dania główne",
        "dania główne",
        "dania główne",
        "desery",
        "zupy",
        "przekąski",
        "dania wegańskie",
        "napoje"
    )

    private val imageNames = listOf(
        "pizza_nea",
        "lasagna",
        "carbonara",
        "sernik",
        "ogor",
        "koreczki",
        "burger",
        "lemoniada"
    )

    private var filteredRecipeNames = recipeNames.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_item_placeholder, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.recipeName?.text = filteredRecipeNames[position]
        holder.recipeIngredients?.text = recipeIngredients[recipeNames.indexOf(filteredRecipeNames[position])]

        val imageName = imageNames[recipeNames.indexOf(filteredRecipeNames[position])]
        val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

        holder.recipeImage?.let { imageView ->
            if (imageResId != 0) {
                Glide.with(context)
                    .load(imageResId)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.placeholder_image)
            }
        }
    }

    override fun getItemCount(): Int = filteredRecipeNames.size

    fun filterByCategory(category: String) {
        filteredRecipeNames = if (category.isEmpty()) {
            recipeNames.toMutableList()
        } else {
            recipeNames.filterIndexed { index, _ ->
                recipeCategories[index].equals(category, ignoreCase = true)
            }.toMutableList()
        }
        println("Filtered recipes: $filteredRecipeNames")
        notifyDataSetChanged()
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



