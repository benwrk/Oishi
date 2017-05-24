package co.bwsc.oishi.model

import io.realm.RealmObject

/**
 * Created by Ben on 5/24/2017.
 */

data class Recipe(val uri: String,
                  val label: String,
                  val image: String,
                  val source: String,
                  val url: String,
                  val dietLabels: List<String>,
                  val healthLabels: List<String>,
                  val cautions: List<String>,
                  val ingredientLines: List<String>,
                  val calories: Double,
                  val totalWeight: Double,
                  val ingredients: List<Ingredient>)
