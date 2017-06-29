package co.bwsc.oishi.favorite

import co.bwsc.oishi.model.Recipe

/**
 * Created by Ben on 5/24/2017.
 */
interface FavoriteContract {
    interface FavoriteView {

        fun showLoading()

        fun hideLoading()

        fun showError(message: String)

        fun updateRecipes(recipes: List<Recipe>)
    }

    interface FavoritePresenter {

        fun loadFavorites()

        fun deleteFavorite(recipe: Recipe)
    }
}