package co.bwsc.oishi.home

import android.content.Context
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
import co.bwsc.oishi.R.id.*
import co.bwsc.oishi.model.Recipe
import co.bwsc.oishi.model.RecipeDetailAdapter
import com.squareup.picasso.Picasso
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ShakeDetector.Listener, HomeContract.HomeView {

    override fun showLoading() {
        collapsing_toolbar_layout.title = getString(R.string.loading) + "..."
        home_image.setImageDrawable(null)
        recycler.adapter = RecipeDetailAdapter(ArrayList<Pair<String, String>>())
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

//        details.add(Pair("Ingredients", ""))

        val ingredients = recipe.ingredients
        ingredients.mapTo(details) { Pair(it.text, String.format("%.2f ", it.weight) + getString(R.string.grams)) }

        recycler.adapter = RecipeDetailAdapter(details)

        Picasso.with(this).load(recipe.image).into(home_image)
    }

    val presenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        collapsing_toolbar_layout.title = getString(R.string.loading) + "..."

        recycler.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            presenter.loadRandomRecipe()
            Snackbar.make(drawer_layout.rootView, getString(R.string.try_shake),
                    Snackbar.LENGTH_SHORT).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        initializeShakeDetector()

        presenter.loadRandomRecipe()
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return if (item.itemId == R.id.action_settings) true else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            nav_camera -> TODO()
            nav_gallery -> TODO()
            nav_slideshow -> TODO()
            nav_manage -> TODO()
            nav_share -> TODO()
            nav_send -> TODO()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun hearShake() {
        Snackbar.make(drawer_layout, getString(R.string.randomizing) + "...", Snackbar.LENGTH_SHORT).show()
        presenter.loadRandomRecipe()
    }
}
