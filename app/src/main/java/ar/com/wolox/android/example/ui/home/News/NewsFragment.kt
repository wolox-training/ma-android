package ar.com.wolox.android.example.ui.home.News

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), IFragmentNews {

    private val newsAdapter = NewsAdapter()

    override fun init() {
        vFabBtn.attachToRecyclerView(vNewsRecyclerView)
        vFabBtn.setOnClickListener() {
            /*
            val fragment = CreateNewsFragment()
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(ar.com.wolox.android.R.id.fragmentNews, CreateNewsFragment(), "CReate news fragment") // give your fragment container id in first parameter
            transaction.addToBackStack(null) // if written, this transaction will be added to backstack
            transaction.commit()
            */
            val fr = CreateNewsFragment()
            val fm = fragmentManager
            val fragmentTransaction = fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.vFragmentNewsss, fr)
            fragmentTransaction.commit()
        }
        vSwipeRefreshNews.setColorSchemeResources(android.R.color.holo_blue_light)
        vSwipeRefreshNews.setOnRefreshListener {
            vSwipeRefreshNews.isRefreshing = true
            presenter.getNews()
            Toast.makeText(context, "Refreshing", Toast.LENGTH_SHORT).show()
        }
        vNewsRecyclerView.adapter = newsAdapter
        vNewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        presenter.getNews()
    }

    override fun layout(): Int {
        return ar.com.wolox.android.R.layout.fragment_news
    }

    override fun showError() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun setNews(listOfNews: List<News>) {
        newsAdapter.setNews(listOfNews)
        newsAdapter.notifyDataSetChanged()
    }
}