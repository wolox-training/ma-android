package ar.com.wolox.android.example.ui.home.News

import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private val mRetrofitServices: RetrofitServices
) : BasePresenter<IFragmentNews>() {

    fun getNews() {
        mRetrofitServices.getService(NewsService::class.java).getNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        runIfViewAttached { view ->
                            view.setNews(it)
                        }
                    }
                    onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.showError() }) }
                    onCallFailure { runIfViewAttached(Runnable { view.showErrorFailToCOnnectWithAPI() }) }
                }
        )
    }
}