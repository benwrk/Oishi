package co.bwsc.oishi.recipe_detail

import co.bwsc.oishi.model.Recipe

/**
 * Created by Ben on 5/24/2017.
 */
interface DetailContract {
    interface DetailView {

        fun showLoading()

        fun hideLoading()

        fun showError(message: String)

        fun showRecipe(recipe: Recipe)
    }

    interface DetailPresenter {

    }
}