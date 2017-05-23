package co.bwsc.oishi.model

import android.net.Uri

/**
 * Created by Ben on 5/24/2017.
 */

data class Recipe(val name: String, val heroImage: Uri, val calories: Double, val nutritionList: List<NutritionInfo>, val ingredients: List<Ingredient>)
