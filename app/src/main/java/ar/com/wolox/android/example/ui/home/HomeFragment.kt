package ar.com.wolox.android.example.ui.home

import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.News.NewsFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject
    internal lateinit var newsFragment: NewsFragment
    @Inject
    internal lateinit var profileFragment: ProfileFragment

    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {
        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair<Fragment, String>(newsFragment, "News"),
                Pair<Fragment, String>(profileFragment, "Profile"))

        vHomeTabLayout.setupWithViewPager(vHomeViewPager)
        vHomeViewPager.adapter = fragmentPagerAdapter
        vHomeTabLayout.getTabAt(0)!!.setIcon(R.drawable.selector_icon_news)
        vHomeTabLayout.getTabAt(1)!!.setIcon(R.drawable.selector_icon_profile)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}