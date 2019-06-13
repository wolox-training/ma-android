package ar.com.wolox.android.example.ui.home.News.Create

import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class CreateNewsFragment @Inject constructor() : WolmoFragment<CreateNewsPresenter>() {

    override fun layout(): Int {
        return ar.com.wolox.android.R.layout.fragment_create_news
    }

    override fun init() {
    }
}