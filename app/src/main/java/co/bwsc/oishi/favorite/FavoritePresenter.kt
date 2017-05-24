package co.bwsc.oishi.favorite

import android.util.Log
import co.bwsc.oishi.BuildConfig
import co.bwsc.oishi.model.Api
import co.bwsc.oishi.model.Ingredient
import co.bwsc.oishi.model.Recipe
import co.bwsc.oishi.model.RecipeResponse
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.firebase.database.DataSnapshot



/**
 * Created by Ben on 5/24/2017.
 */
class FavoritePresenter(val view: FavoriteContract.FavoriteView) : FavoriteContract.FavoritePresenter {
    override fun loadFavorites() {
        FirebaseDatabase.getInstance().getReference("Recipes").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val objects = p0?.value as HashMap<String, Recipe>
                var recipes = ArrayList<Recipe>()
                for (recipesSnapshot in p0.children) {
                    recipes.add(Recipe(
                            recipesSnapshot.child("uri").value as String,
                            recipesSnapshot.child("label").value as String,
                            recipesSnapshot.child("image").value as String,
                            recipesSnapshot.child("source").value as String,
                            recipesSnapshot.child("url").value as String,
                            recipesSnapshot.child("dietLabels").value as List<String>,
                            recipesSnapshot.child("healthLabels").value as List<String>,
                            ArrayList<String>(),
                            recipesSnapshot.child("calories").value as Double,
                            recipesSnapshot.child("totalWeight").value as Double,
                            recipesSnapshot.child("ingredients").value as List<Ingredient>
                    ))
                }
                println(recipes)
                view.loadData(recipes)
//                println(objects)
            }

        })
    }
}