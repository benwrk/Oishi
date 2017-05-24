package co.bwsc.oishi.model

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ben on 5/24/2017.
 */
class Api {
    companion object {
        val edamamApiBaseUrl = "https://api.edamam.com/"
        fun getRecipesApi(): RecipeApi {
            val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
            val retrofit = Retrofit.Builder().baseUrl(edamamApiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
            return retrofit.create(RecipeApi::class.java)
        }
    }
}