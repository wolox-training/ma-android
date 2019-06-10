package ar.com.wolox.android.example.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R

class NewsFragment : Fragment() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var listOfNews: ArrayList<News> = ArrayList<News>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        */
        // homeRecyclerView.
        this.fillArrayListNews()
        var view: View = inflater.inflate(R.layout.fragment_news, container, false)
        // var homeRecyclerViewWithR: RecyclerView = R.id.homeRecyclerView as RecyclerView
        var homeRecyclerViewWithR: RecyclerView = view.findViewById(R.id.homeRecyclerView) as RecyclerView
        homeRecyclerViewWithR?.adapter = HomeRecyclerViewAdapter(listOfNews)
        homeRecyclerViewWithR?.layoutManager = LinearLayoutManager(this.context) as RecyclerView.LayoutManager?
        // homeRecyclerView?.layoutManager = LinearLayoutManager(context)
        // homeRecyclerView?.adapter = HomeRecyclerViewAdapter(news)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*viewManager = LinearLayoutManager(activity)
        var listOfNews: ArrayList<News> = ArrayList<News>()
        viewAdapter = HomeRecyclerViewAdapter(listOfNews)
        */
    }

    fun fillArrayListNews() {
        var news: News = News("Ali Connors", "I'll be in your .. ", "15 min")
        listOfNews.add(news)
    }
}