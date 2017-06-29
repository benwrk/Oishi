package co.bwsc.oishi.favorite

import co.bwsc.oishi.model.Ingredient
import co.bwsc.oishi.model.Recipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


/**
 * Created by Ben on 5/24/2017.
 */
class FavoritePresenter(val view: FavoriteContract.FavoriteView) : FavoriteContract.FavoritePresenter {
    override fun deleteFavorite(recipe: Recipe) {
        FirebaseDatabase.getInstance().getReference("Recipes").child(recipe.key).removeValue()
    }

    override fun loadFavorites() {
        FirebaseDatabase.getInstance().getReference("Recipes").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            @Suppress("UNCHECKED_CAST")
            override fun onDataChange(p0: DataSnapshot?) {
                val recipes = ArrayList<Recipe>()
                if (p0 != null) {
                    p0.children.mapTo(recipes) { it ->
                        Recipe(
                                it.key,
                                it.child("uri").value as String?,
                                it.child("label").value as String?,
                                it.child("image").value as String?,
                                it.child("source").value as String?,
                                it.child("url").value as String?,
                                it.child("dietLabels").value as List<String>?,
                                it.child("healthLabels").value as List<String>?,
                                it.child("cautions").value as List<String>?,
                                it.child("ingredientLines").value as List<String>?,
                                (it.child("calories").value as Number?)?.toDouble(),
                                (it.child("totalWeight").value as Number?)?.toDouble(),
                                it.child("ingredients").value as List<Ingredient>?
                        )
                    }
                }
                view.updateRecipes(recipes)
            }
        })
    }
}