package co.bwsc.oishi.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Ben on 5/24/2017.
 */
interface RecipeApi {
    @GET("search")
    fun getRecipes(@QueryMap params: Map<String, String>): Call<RecipeResponse>
}


