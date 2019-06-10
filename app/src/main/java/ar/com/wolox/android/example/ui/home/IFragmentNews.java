package ar.com.wolox.android.example.ui.home;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Fragment news interface
 */
public interface IFragmentNews {
    void onGetNewsSuccess(@Nullable ArrayList<News> listOfNews);
    void onGetNewsNotFound();
    void onGetNewsFailed();
}
