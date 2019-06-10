package ar.com.wolox.android.example.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R

class HomeRecyclerViewAdapter(val listOfNews: ArrayList<News>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewAdapter.ViewHolder {
        // val v = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        // return ViewHolder(v)
        // return UserViewHolder(inflate(ar.com.wolox.android.R.layout.item_user, parent), getListener())
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: News = listOfNews[position]
        holder.author.text = news.title
        holder.newsText.text = news.text
        // holder.time.text = news.time
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val author = itemView.findViewById<TextView>(ar.com.wolox.android.R.id.textViewAuthor) as TextView
        val time = ar.com.wolox.android.R.id.textViewTime as TextView
        val newsText = ar.com.wolox.android.R.id.textViewText as TextView
        */
        var author = itemView.findViewById(R.id.textViewAuthor) as TextView
        var time = itemView.findViewById(R.id.textViewTime) as TextView
        var newsText = itemView.findViewById(R.id.textViewText) as TextView
        var imageViewHeart: ImageView = itemView.findViewById(R.id.imageViewHeart)
        var imageViewNews: ImageView = itemView.findViewById(R.id.imageViewNews)
    }
}