package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.example.network.APIAdapter
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(private val apiAdapter: APIAdapter) : BasePresenter<IFragmentNews>(), OnHomeNewsListener {
    // private lateinit var apiAdapter: APIAdapter
    private lateinit var newsService: NewsService
    // @Inject constructor(private val mUserSession: UserSession)
    /*
    @Inject
    internal fun HomePresenter(apiAdapter: APIAdapter) {
        this.apiAdapter = apiAdapter
    }
    */
    override fun onGetNewsSuccess(listOfNews: ArrayList<News>) {
        view?.onGetNewsSuccess(listOfNews)
    }

    override fun onGetNewsNotFound() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetNewsFailed() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
        getView().onGetNewsFailed()
    }

    fun getNews() {
        apiAdapter.getNews(this)
    }
}