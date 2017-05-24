package co.bwsc.oishi.search

import co.bwsc.oishi.model.Recipe

/**
 * Created by Ben on 5/24/2017.
 */
interface SearchContract {
    interface SearchView {

        fun showLoading()

        fun hideLoading()

        fun showError(message: String)

        fun showRecipes(recipes: List<Recipe>)
    }

    interface SearchPresenter {
        fun getRecipes(query: String)
    }
}