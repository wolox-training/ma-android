package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.example.ui.home.News.Create.CreateNewsFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

/**
 * Home activity
 */
class HomeActivity @Inject constructor() : WolmoActivity() {

    override fun layout(): Int = ar.com.wolox.android.R.layout.activity_base

    override fun init() {
        replaceFragment(ar.com.wolox.android.R.id.vActivityBaseContent, HomeFragment.newInstance())
    }

    fun switchToCreateNewsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(ar.com.wolox.android.R.id.vActivityBaseContent, CreateNewsFragment())
                .addToBackStack(null)
                .commit()
    }
}