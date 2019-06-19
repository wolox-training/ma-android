package ar.com.wolox.android.example.ui.home.News.Create

import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

/**
 * Home activity
 */
class CreateNewsActivity @Inject constructor() : WolmoActivity() {

    override fun layout(): Int = ar.com.wolox.android.R.layout.activity_base

    override fun init() {
        replaceFragment(ar.com.wolox.android.R.id.vActivityBaseContent, CreateNewsFragment.newInstance())
    }
}