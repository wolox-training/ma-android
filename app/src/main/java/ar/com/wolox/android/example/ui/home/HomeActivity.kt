package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

/**
 * Home activity
 */
class HomeActivity @Inject constructor() : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, HomeFragment.newInstance())
    }
}