package co.bwsc.oishi.home

import android.util.Log
import co.bwsc.oishi.BuildConfig
import co.bwsc.oishi.model.Api
import co.bwsc.oishi.model.RecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ben on 5/24/2017.
 */
class HomePresenter(val view: HomeContract.HomeView) : HomeContract.HomePresenter {
    override fun loadRandomRecipe() {
        view.showLoading()

        val keywords = listOf("almond", "apple", "avocado", "bacon", "banana", "beef", "beverages", "butter", "cabbage", "carrot", "cassava", "cheese", "chicken", "chickpea", "chocolate", "clam", "coconut", "coffee", "crab", "dough", "egg", "eggplant", "fish", "flowers", "fruit", "goat", "lamb", "legume", "lemon", "maple", "maize", "milk", "melon", "mushroom", "noodle", "onion", "pasta", "peanut", "plum", "poppy", "pork", "potato", "rice", "sausage", "seafood", "shrimp", "soups", "soy", "strawberry", "toast", "tofu", "tomato", "tortilla", "veal", "vegetable", "yogurt")
        val randomQuery = keywords[(Math.random() * keywords.size).toInt()]

        Api.getRecipesApi().getRecipes(
                mapOf(
                        Pair("q", randomQuery),
                        Pair("app_id", BuildConfig.EDAMAM_APP_ID),
                        Pair("app_key", BuildConfig.EDAMAM_APP_KEY)
                )).enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>?, response: Response<RecipeResponse>?) {
                val responses = response!!.body()!!.hits
                Log.d(this.javaClass.simpleName, responses.toString())
                view.updateRecipe(responses[(Math.random() * responses.size).toInt()].recipe)
            }

            override fun onFailure(call: Call<RecipeResponse>?, t: Throwable?) {
                Log.e(this.javaClass.simpleName, "Error getting recipes")
            }
        })
    }
}