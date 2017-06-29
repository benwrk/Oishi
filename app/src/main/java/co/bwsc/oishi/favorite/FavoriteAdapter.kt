package co.bwsc.oishi.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import co.bwsc.oishi.R
import co.bwsc.oishi.model.Recipe
import com.squareup.picasso.Picasso

/**
 * Created by Ben on 5/24/2017.
 */
class FavoriteAdapter(val titles: List<Recipe>, val onRecipeDeleteClickListener: OnRecipeDeleteClickListener) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = titles[position]
        holder.recipe = item
        holder.label.text = item.label
        holder.calorie.text = String.format("%.2f", item.calories)
        Picasso.with(holder.itemView.context).load(item.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_jumbo, parent, false)
        return ViewHolder(view, onRecipeDeleteClickListener)
    }


    inner class ViewHolder(val view: View, val onRecipeDeleteClickListener: OnRecipeDeleteClickListener) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById(R.id.info_image) as ImageView
        val label = view.findViewById(R.id.info_label) as TextView
        val calorie = view.findViewById(R.id.info_calorie) as TextView
        val calorieUnit = view.findViewById(R.id.info_calorie_unit) as TextView
        val deleteButton = view.findViewById(R.id.info_delete) as Button
        var recipe: Recipe? = null

        init {
            deleteButton.setOnClickListener({
                if (recipe != null) {
                    onRecipeDeleteClickListener.onRecipeDeleteClick(recipe!!)
                }
            })
        }
    }

    interface OnRecipeDeleteClickListener {
        fun onRecipeDeleteClick(recipe: Recipe)
    }
}

