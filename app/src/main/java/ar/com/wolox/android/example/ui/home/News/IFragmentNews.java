package ar.com.wolox.android.example.ui.home.News;

import java.util.List;

import ar.com.wolox.android.example.model.News;

/**
 * Fragment news interface
 */
public interface IFragmentNews {

    void showError();

    void setNews(List<News> response);
}
