package ar.com.wolox.android.example.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.APIAdapter

class NewsFragment : Fragment(), IFragmentNews {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    // private var listOfNews: ArrayList<News> = ArrayList<News>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // this.fillArrayListNews()
        // var view: View = inflater.inflate(R.layout.fragment_news, container, false)
        // var homeRecyclerViewWithR: RecyclerView = R.id.homeRecyclerView as RecyclerView
        // return inflater.inflate(R.layout.fragment_news, container, false) as View
        var view: View = inflater.inflate(R.layout.fragment_news, container, false)
        // TODO: CHANGE THIS
        var presenter: HomePresenter = HomePresenter(APIAdapter())
        presenter.getNews()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*
        var homeRecyclerViewWithR: RecyclerView = view.findViewById(R.id.homeRecyclerView) as RecyclerView
        homeRecyclerViewWithR?.adapter = HomeRecyclerViewAdapter(listOfNews)
        homeRecyclerViewWithR?.layoutManager = LinearLayoutManager(this.context) as RecyclerView.LayoutManager?
        */
    }
    /*
    fun fillArrayListNews() {
        var news: News = News("Ali Connors", "I'll be in your .. ", "15 min")
        listOfNews.add(news)
    }
    */
    override fun onGetNewsSuccess(listOfNews: ArrayList<News>?) {
        var homeRecyclerViewWithR: RecyclerView = view?.findViewById(R.id.homeRecyclerView) as RecyclerView
        homeRecyclerViewWithR?.adapter = HomeRecyclerViewAdapter(listOfNews as ArrayList<News>)
        homeRecyclerViewWithR?.layoutManager = LinearLayoutManager(this.context) as RecyclerView.LayoutManager?
    }

    override fun onGetNewsNotFound() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetNewsFailed() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}