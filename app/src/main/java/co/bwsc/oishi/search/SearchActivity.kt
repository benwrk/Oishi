package co.bwsc.oishi.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.bwsc.oishi.R
import co.bwsc.oishi.model.Recipe

class SearchActivity : AppCompatActivity(), SearchContract.SearchView {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRecipes(recipes: List<Recipe>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (!intent.hasExtra(getString(R.string.search_query_extra_key))) {
            finish()
        }

        val query = intent.getStringExtra(getString(R.string.search_query_extra_key))
    }
}
