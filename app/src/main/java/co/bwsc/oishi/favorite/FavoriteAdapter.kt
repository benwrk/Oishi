package co.bwsc.oishi.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.bwsc.oishi.R
import co.bwsc.oishi.model.Recipe

/**
 * Created by Ben on 5/24/2017.
 */
class FavoriteAdapter(val titles: List<Recipe>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = titles[position]
        holder.label.text = item.label
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_jumbo, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById(R.id.info_image) as ImageView
        var label = itemView.findViewById(R.id.info_label) as TextView
        var calorie = itemView.findViewById(R.id.info_calorie) as TextView
        var calorieUnit = itemView.findViewById(R.id.info_calorie_unit) as TextView
//        var subtitle: TextView = itemView.findViewById(R.id.item_text_subtitle) as TextView
    }
}