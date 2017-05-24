//package co.bwsc.oishi.recipe_detail
//
//import android.content.Context
//import android.hardware.SensorManager
//import android.os.Bundle
//import android.support.design.widget.NavigationView
//import android.support.design.widget.Snackbar
//import android.support.v4.view.GravityCompat
//import android.support.v7.app.ActionBarDrawerToggle
//import android.support.v7.app.AppCompatActivity
//import android.support.v7.widget.LinearLayoutManager
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.Toast
//import co.bwsc.oishi.R
//import co.bwsc.oishi.home.HomeContract
//import co.bwsc.oishi.home.HomePresenter
//import co.bwsc.oishi.model.Recipe
//import co.bwsc.oishi.home.HomeDetailAdapter
//import com.squareup.picasso.Picasso
//import com.squareup.seismic.ShakeDetector
//import kotlinx.android.synthetic.main.activity_home.*
//import kotlinx.android.synthetic.main.app_bar_home.*
//import com.miguelcatalan.materialsearchview.MaterialSearchView
//
//
//
//
//class DetailActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ShakeDetector.Listener, HomeContract.HomeView {
//    override fun showLoading() {
//        kotlinx.android.synthetic.main.app_bar_home.collapsing_toolbar_layout.title = getString(R.string.loading) + "..."
//        kotlinx.android.synthetic.main.app_bar_home.home_image.setImageDrawable(null)
//        kotlinx.android.synthetic.main.content_home.recycler.adapter = HomeDetailAdapter(ArrayList<Pair<String, String>>())
//    }
//
//    override fun hideLoading() {
//
//    }
//
//    override fun showError(message: String) {
//        Snackbar.make(kotlinx.android.synthetic.main.activity_home.drawer_layout, message, Snackbar.LENGTH_SHORT).show()
//    }
//
//    override fun updateRecipe(recipe: Recipe) {
//        kotlinx.android.synthetic.main.app_bar_home.collapsing_toolbar_layout.title = recipe.label
//
//        val details = ArrayList<Pair<String, String>>()
//        details.add(Pair(String.format("%.2f", recipe.calories), "Kilo-calories"))
//
//        val ingredients = recipe.ingredients
//        ingredients.mapTo(details) { Pair(it.text, String.format("%.2f ", it.weight) + getString(R.string.grams)) }
//
//        kotlinx.android.synthetic.main.content_home.recycler.adapter = HomeDetailAdapter(details)
//
//        Picasso.with(this).load(recipe.image).into(kotlinx.android.synthetic.main.app_bar_home.home_image)
//    }
//
//    val presenter = HomePresenter(this)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//        setSupportActionBar(kotlinx.android.synthetic.main.app_bar_home.toolbar)
//        kotlinx.android.synthetic.main.app_bar_home.collapsing_toolbar_layout.title = getString(R.string.loading) + "..."
//
//        kotlinx.android.synthetic.main.content_home.recycler.layoutManager = LinearLayoutManager(this)
//
//        kotlinx.android.synthetic.main.app_bar_home.fab.setOnClickListener {
//            presenter.loadRandomRecipe()
//            Snackbar.make(kotlinx.android.synthetic.main.activity_home.drawer_layout.rootView, getString(R.string.try_shake),
//                    Snackbar.LENGTH_SHORT).show()
//        }
//
//        val toggle = ActionBarDrawerToggle(
//                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
////        drawer_layout.addDrawerListener(toggle)
////        toggle.syncState()
//
//        kotlinx.android.synthetic.main.activity_home.nav_view.setNavigationItemSelectedListener(this)
//        initializeShakeDetector()
//        initializeSearch()
//
//        presenter.loadRandomRecipe()
//    }
//
//    private fun initializeSearch() {
//        kotlinx.android.synthetic.main.app_bar_home.search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                Toast.makeText(baseContext, query, Toast.LENGTH_LONG).show()
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                Toast.makeText(baseContext, newText, Toast.LENGTH_LONG).show()
//                return false
//            }
//        })
//
//        kotlinx.android.synthetic.main.app_bar_home.search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
//            override fun onSearchViewShown() {
//                //Do some magic
//            }
//
//            override fun onSearchViewClosed() {
//                //Do some magic
//            }
//        })
////        searchView.queryHint = "Search recipes"
////        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
////            override fun onQueryTextSubmit(query: String): Boolean {
////                Toast.makeText(baseContext, query, Toast.LENGTH_LONG).show()
////                return false
////            }
////
////            override fun onQueryTextChange(newText: String): Boolean {
////                Toast.makeText(baseContext, newText, Toast.LENGTH_LONG).show()
////                return false
////            }
////        })
//
////        val searchView = findViewById(R.id.search_view) as SearchView
////// Sets searchable configuration defined in searchable.xml for this SearchView
////        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
////        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
////
////        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
////            override fun onQueryTextSubmit(query: String): Boolean {
//////                searchFor(query)
////                Toast.makeText(this@HomeActivity, query, Toast.LENGTH_SHORT).show()
////                return true
////            }
////
////            override fun onQueryTextChange(query: String): Boolean {
//////                filterSearchFor(query)
////                Toast.makeText(this@HomeActivity, query, Toast.LENGTH_SHORT).show()
////                return true
////            }
////        })
//    }
//
//    fun initializeShakeDetector() {
//        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        val sd = ShakeDetector(this)
//        sd.start(sensorManager)
//    }
//
//    override fun onBackPressed() {
//        if (kotlinx.android.synthetic.main.activity_home.drawer_layout.isDrawerOpen(GravityCompat.START)) {
//            kotlinx.android.synthetic.main.activity_home.drawer_layout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        kotlinx.android.synthetic.main.app_bar_home.search_view.setMenuItem(menu.findItem(R.id.action_search))
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
////        return if (item.itemId == R.id.action_settings) true else super.onOptionsItemSelected(item)
////        when (item.itemId) {
////            action_search -> {
////                val intent = Intent(this, SearchActivity::class.java)
////                startActivity(intent)
////            }
////        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//        when (item.itemId) {
////            nav_camera -> TODO()
////            nav_gallery -> TODO()
////            nav_slideshow -> TODO()
////            nav_manage -> TODO()
////            nav_share -> TODO()
////            nav_send -> TODO()
//        }
//        kotlinx.android.synthetic.main.activity_home.drawer_layout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    override fun hearShake() {
//        Snackbar.make(kotlinx.android.synthetic.main.activity_home.drawer_layout.rootView, getString(R.string.randomizing) + "...", Snackbar.LENGTH_SHORT).show()
//        presenter.loadRandomRecipe()
//    }
//
//}
