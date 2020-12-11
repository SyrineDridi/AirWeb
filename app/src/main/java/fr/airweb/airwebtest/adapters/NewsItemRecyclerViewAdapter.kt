package fr.airweb.airwebtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.airweb.airwebtest.R
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.utils.CellClickListener

class NewsItemRecyclerViewAdapter(
    private val context: Context,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder>() {

    private var list = listOf<NewsDetails>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_list_news_item, parent, false)
        return ViewHolder(view)
    }

    fun setNewsList(mDeveloperModel: List<NewsDetails>) {
        this.list = mDeveloperModel
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.titleNews.text = item.title
        holder.contentNews.text = item.content
        Glide.with(context)
            .load(item.picture)
            .into(holder.imageNews)
        holder.itemView.setOnClickListener { cellClickListener.onCellClickListener(item) }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleNews: TextView = view.findViewById(R.id.item_title)
        val imageNews: ImageView = view.findViewById(R.id.item_image)
        val contentNews: TextView = view.findViewById(R.id.item_content)
    }
}