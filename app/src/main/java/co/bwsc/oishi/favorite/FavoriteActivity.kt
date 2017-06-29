package co.bwsc.oishi.favorite

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import co.bwsc.oishi.R
import co.bwsc.oishi.model.Recipe
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), FavoriteContract.FavoriteView {

    val presenter = FavoritePresenter(this)
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRecipes(recipes: List<Recipe>) {
        if (!recipes.isEmpty()) {
            favorite_recycler.adapter = FavoriteAdapter(recipes, object : FavoriteAdapter.OnRecipeDeleteClickListener {
                override fun onRecipeDeleteClick(recipe: Recipe) {
                    presenter.deleteFavorite(recipe)
                }
            })
        } else {
            Toast.makeText(this, "No saved recipes!", Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        title = "Favorites"
        favorite_recycler.layoutManager = LinearLayoutManager(this)
        presenter.loadFavorites()
    }
}
