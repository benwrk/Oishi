package co.bwsc.oishi.home

import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import co.bwsc.oishi.R
import co.bwsc.oishi.model.Recipe
import co.bwsc.oishi.search.SearchActivity
import com.squareup.picasso.Picasso
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import com.miguelcatalan.materialsearchview.MaterialSearchView




open class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ShakeDetector.Listener, HomeContract.HomeView {
    override fun showLoading() {
        collapsing_toolbar_layout.title = getString(R.string.loading) + "..."
        home_image.setImageDrawable(null)
        home_recycler.adapter = HomeDetailAdapter(ArrayList<Pair<String, String>>())
    }

    override fun hideLoading() {

    }

    override fun showError(message: String) {
        Snackbar.make(drawer_layout, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateRecipe(recipe: Recipe) {
        collapsing_toolbar_layout.title = recipe.label

        val details = ArrayList<Pair<String, String>>()
        details.add(Pair(String.format("%.2f", recipe.calories), "Kilo-calories"))

        val ingredients = recipe.ingredients
        ingredients.mapTo(details) { Pair(it.text, String.format("%.2f ", it.weight) + getString(R.string.grams)) }

        home_recycler.adapter = HomeDetailAdapter(details)

        Picasso.with(this).load(recipe.image).into(home_image)
    }

    val presenter = HomePresenter(this)

    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        collapsing_toolbar_layout.title = getString(R.string.loading) + "..."

        home_recycler.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            presenter.loadRandomRecipe()
            Snackbar.make(drawer_layout.rootView, getString(R.string.try_shake),
                    Snackbar.LENGTH_SHORT).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        nav_view.setNavigationItemSelectedListener(this)
        initializeShakeDetector()
        initializeSearch()

        presenter.loadRandomRecipe()
    }

    private fun initializeSearch() {
        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                Toast.makeText(baseContext, query, Toast.LENGTH_LONG).show()
                val intent = Intent(this@HomeActivity, SearchActivity::class.java)
                intent.putExtra(getString(R.string.search_query_extra_key), query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
//                Toast.makeText(baseContext, newText, Toast.LENGTH_LONG).show()
                return false
            }
        })

        search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                //Do some magic
            }

            override fun onSearchViewClosed() {
                //Do some magic
            }
        })
    }

    fun initializeShakeDetector() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.start(sensorManager)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        search_view.setMenuItem(menu.findItem(R.id.action_search))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
//            nav_camera -> TODO()
//            nav_gallery -> TODO()
//            nav_slideshow -> TODO()
//            nav_manage -> TODO()
//            nav_share -> TODO()
//            nav_send -> TODO()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun hearShake() {
        Snackbar.make(drawer_layout.rootView, getString(R.string.randomizing) + "...", Snackbar.LENGTH_SHORT).show()
        presenter.loadRandomRecipe()
    }

}
