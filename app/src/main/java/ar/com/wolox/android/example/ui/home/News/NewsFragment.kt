package ar.com.wolox.android.example.ui.home.News

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.home.News.Create.CreateNewsFragment
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_base.view.*
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), IFragmentNews {

    private val newsAdapter = NewsAdapter()

    override fun init() {
        vFabBtn.attachToRecyclerView(vNewsRecyclerView)
        vFabBtn.setOnClickListener() {
            var homeActivity = activity as HomeActivity
            fragmentManager?.beginTransaction()!!
                    .replace(homeActivity.vActivityBaseContent.id, CreateNewsFragment())
                    .addToBackStack(null)
                    .commit()
        }
        vSwipeRefreshNews.setColorSchemeResources(android.R.color.holo_blue_light)
        vSwipeRefreshNews.setOnRefreshListener {
            vSwipeRefreshNews.isRefreshing = true
            presenter.getNews()
        }
        vNewsRecyclerView.adapter = newsAdapter
        vNewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        presenter.getNews()
    }

    override fun layout(): Int {
        return ar.com.wolox.android.R.layout.fragment_news
    }

    override fun showError() {
        newsAdapter.clear()
        newsAdapter.notifyDataSetChanged()
        Toast.makeText(context, "No hay noticias cargadas.", Toast.LENGTH_LONG).show()
        stopRefreshing()
    }

    override fun showErrorFailToCOnnectWithAPI() {
        newsAdapter.clear()
        newsAdapter.notifyDataSetChanged()
        Toast.makeText(context, "ERROR - No se pudo conectar a la API", Toast.LENGTH_LONG).show()
        stopRefreshing()
    }

    override fun setNews(listOfNews: List<News>) {
        newsAdapter.setNews(listOfNews)
        newsAdapter.notifyDataSetChanged()
        stopRefreshing()
    }

    fun stopRefreshing() {
        vSwipeRefreshNews.isRefreshing = false
    }
}