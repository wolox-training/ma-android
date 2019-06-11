package ar.com.wolox.android.example.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView

class HomeRecyclerViewAdapter(val listOfNews: ArrayList<News>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewAdapter.ViewHolder {
        // val v = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        // return ViewHolder(v)
        // return UserViewHolder(inflate(ar.com.wolox.android.R.layout.item_user, parent), getListener())
        val view = LayoutInflater.from(parent.context).inflate(ar.com.wolox.android.R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: News = listOfNews[position]
        holder.author.text = news.title
        holder.newsText.text = news.text
        holder.time.text = news.getTimeReference()
        // Gets image with fresco
        holder.draweeViewNews.setImageURI(Uri.parse(news.picture))
        // holder.time.text = news.time
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val author = itemView.findViewById<TextView>(ar.com.wolox.android.R.id.textViewAuthor) as TextView
        val time = ar.com.wolox.android.R.id.textViewTime as TextView
        val newsText = ar.com.wolox.android.R.id.textViewText as TextView
        */
        var author = itemView.findViewById(ar.com.wolox.android.R.id.textViewAuthor) as TextView
        var time = itemView.findViewById(ar.com.wolox.android.R.id.textViewTime) as TextView
        var newsText = itemView.findViewById(ar.com.wolox.android.R.id.textViewText) as TextView
        var imageViewHeart: ImageView = itemView.findViewById(ar.com.wolox.android.R.id.imageViewHeart)
        val draweeViewNews = itemView.findViewById(ar.com.wolox.android.R.id.newsImageView) as SimpleDraweeView
    }
}