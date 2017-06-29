package co.bwsc.oishi.model

/**
 * Created by Ben on 5/24/2017.
 */

data class Recipe(var key: String,
                  var uri: String?,
                  var label: String?,
                  var image: String?,
                  var source: String?,
                  var url: String?,
                  var dietLabels: List<String>?,
                  var healthLabels: List<String>?,
                  var cautions: List<String>?,
                  var ingredientLines: List<String>?,
                  var calories: Double?,
                  var totalWeight: Double?,
                  var ingredients: List<Ingredient>?) {
    constructor() : this("", "", "", "", "", "", ArrayList<String>(), ArrayList<String>(), ArrayList<String>(), ArrayList<String>(), 0.0, 0.0, ArrayList<Ingredient>())
}
