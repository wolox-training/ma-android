package ar.com.wolox.android.example.ui.home.News

import ar.com.wolox.android.example.model.News

interface ICreateNews {

    fun showError()

    fun setNews(response: List<News>)
}
