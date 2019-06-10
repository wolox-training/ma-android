package ar.com.wolox.android.example.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Home activity
 */
class HomeActivity : AppCompatActivity() {
    private val NEWS: String = "News"
    private val PROFILE: String = "Profile"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ar.com.wolox.android.R.layout.activity_home)
        this.setupViewPager()
        tabLayout.getTabAt(0)!!.setIcon(ar.com.wolox.android.R.drawable.ic_news_list_on)
        tabLayout.getTabAt(1)!!.setIcon(ar.com.wolox.android.R.drawable.ic_profile_off)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        tab.setIcon(ar.com.wolox.android.R.drawable.ic_news_list_on)
                    }
                    1 -> {
                        tab.setIcon(ar.com.wolox.android.R.drawable.ic_profile_on)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        tab.setIcon(ar.com.wolox.android.R.drawable.ic_news_list_off)
                    }
                    1 -> {
                        tab.setIcon(ar.com.wolox.android.R.drawable.ic_profile_off)
                    }
                }
            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
                //
            }
        })
    }
    private fun setupViewPager() {
        val adapter = HomeFragmentAdapter(getSupportFragmentManager())
        var newsFragment: NewsFragment = NewsFragment()
        var profilaFragment: ProfilaFragment = ProfilaFragment()
        adapter.addFragment(newsFragment, NEWS)
        adapter.addFragment(profilaFragment, PROFILE)
        homeViewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(homeViewPager)
    }
}
