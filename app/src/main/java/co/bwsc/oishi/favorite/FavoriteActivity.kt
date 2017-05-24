package co.bwsc.oishi.favorite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import co.bwsc.oishi.R
import co.bwsc.oishi.model.Recipe
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), FavoriteContract.FavoriteView {
    override fun loadData(list: List<Recipe>) {
        favorite_recycler.adapter = FavoriteAdapter(list)
    }

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        favorite_recycler.layoutManager = LinearLayoutManager(this)
        presenter.loadFavorites()
    }
}
