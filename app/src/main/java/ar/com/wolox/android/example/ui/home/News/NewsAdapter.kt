package ar.com.wolox.android.example.ui.home.News

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var listOfNews = arrayListOf<News>()
    private lateinit var view: View
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        view = LayoutInflater.from(parent.context).inflate(ar.com.wolox.android.R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    fun setNews(listOfNews: List<News>) {
        this.listOfNews.clear()
        this.listOfNews.addAll(listOfNews)
    }

    fun clear() {
        this.listOfNews.clear()
    }

    fun addNews(listOfNews: List<News>) {
        this.listOfNews.addAll(listOfNews)
    }

    override fun getItemCount(): Int {
        return listOfNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = listOfNews[position]
        var url: String = news.picture
        // the url must be https//. Otherwise, it does not work
        url = url.substring(0, 4) + "s" + url.substring(4, url.length)
        val radius = context.resources.getDimensionPixelSize(R.dimen.news_picture_corner_radius)
        holder.apply {
            author.text = news.title
            newsText.text = news.text
            time.text = news.getTimeReference()
            Glide.with(holder.newsPicture.context).load(url)
                    .transform(RoundedCorners(radius))
                    .into(holder.newsPicture)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author = itemView.findViewById(ar.com.wolox.android.R.id.vTextViewNewsTitle) as TextView
        var time = itemView.findViewById(ar.com.wolox.android.R.id.vTextViewNewsTime) as TextView
        var newsText = itemView.findViewById(ar.com.wolox.android.R.id.vTextViewNewsText) as TextView
        val newsPicture = itemView.findViewById(ar.com.wolox.android.R.id.vImageViewNewsPicture) as ImageView
    }
}