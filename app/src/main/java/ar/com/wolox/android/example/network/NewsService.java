package ar.com.wolox.android.example.network;

import java.util.List;

import ar.com.wolox.android.example.ui.home.News;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *  News interface with the API
 */
public interface NewsService {
    @GET("/news")
    Call<List<News>> getNews();
}
