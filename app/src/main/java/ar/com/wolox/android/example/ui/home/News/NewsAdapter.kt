package ar.com.wolox.android.example.ui.home.News

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.example.model.News
import com.bumptech.glide.Glide

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

    fun addNews(listOfNews: List<News>) {
        this.listOfNews.addAll(listOfNews)
    }

    override fun getItemCount(): Int {
        return listOfNews.size
    }
    var urls = arrayOf("http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg", "", "", "")

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: News = listOfNews[position]
        holder.author.text = news.title
        holder.newsText.text = news.text
        holder.time.text = news.getTimeReference()
        var url: String = news.picture
        // Glide.with(holder.newsPicture.context).load("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg")
        Glide.with(holder.newsPicture.context).load(urls[position])
                .override(225, 225)
                .into(holder.newsPicture)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author = itemView.findViewById(ar.com.wolox.android.R.id.vTextViewNewsTitle) as TextView
        var time = itemView.findViewById(ar.com.wolox.android.R.id.vTextViewNewsTime) as TextView
        var newsText = itemView.findViewById(ar.com.wolox.android.R.id.vTextViewNewsText) as TextView
        val newsPicture = itemView.findViewById(ar.com.wolox.android.R.id.vImageViewNewsPicture) as ImageView
    }

    companion object {
        const val MAX_LENGTH_TITLE = 15
        const val MAX_LENGTH_TEXT = 80
    }
}