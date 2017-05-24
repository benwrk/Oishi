package co.bwsc.oishi.model

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.bwsc.oishi.R

/**
 * Created by Ben on 5/24/2017.
 */
class RecipeDetailAdapter(val titles: List<Pair<String, String>>) : RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = titles[position]
        holder.title.text = item.first
        holder.subtitle.text = item.second
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_text, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_text_title) as TextView
        var subtitle: TextView = itemView.findViewById(R.id.item_text_subtitle) as TextView
    }
}