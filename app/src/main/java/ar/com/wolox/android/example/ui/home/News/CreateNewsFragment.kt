package ar.com.wolox.android.example.ui.home.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.com.wolox.android.R

class CreateNewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_news, container, false)
    }
}
/*
class CreateNewsFragment @Inject constructor() : WolmoFragment<CreateNewsPresenter>() {

    override fun layout(): Int {
        return ar.com.wolox.android.R.layout.fragment_create_news
    }

    override fun init() {
    }
}*/