package co.bwsc.oishi.home

import co.bwsc.oishi.model.Recipe

/**
 * Created by Ben on 5/24/2017.
 */
interface HomeContract {
    interface HomeView {

        fun showLoading()

        fun hideLoading()

        fun showError(message: String)

        fun updateRecipe(recipe: Recipe)
    }

    interface HomePresenter {

        fun loadRandomRecipe()
    }
}