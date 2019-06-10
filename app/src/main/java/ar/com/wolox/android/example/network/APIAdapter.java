package ar.com.wolox.android.example.network;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.example.model.APIClient;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.ui.home.News;
import ar.com.wolox.android.example.ui.home.OnHomeNewsListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Adapter class for API
 */
public class APIAdapter {
    private UserService userService;
    private NewsService newsService;

    @Inject
    public APIAdapter() {
        userService = APIClient.getRetrofitClient().create(UserService.class);
        newsService = APIClient.getRetrofitClient().create(NewsService.class);
    }

    /**
     * @param mail - Mail entered by user
     * @param password - Password entered by user
     * @param listener - Invoked on login complete
     */
    public void getUserById(String mail, String password, OnLoginListener listener) {
        //userService = APIClient.getRetrofitClient().create(UserService.class);
        Call<List<User>> call = userService.getUserByMail(mail, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    if (response.body().size() != 0) {
                        listener.onLoginSuccess();
                    } else {
                        listener.onLoginUserNotFound();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onLoginFail();
            }
        });
    }

    /**
     * @param listener - Used to interact with HomePresenter
     */
    public void getNews(OnHomeNewsListener listener) {
        //newsService = APIClient.getRetrofitClient().create(UserService.class);
        Call<List<News>> call = (Call<List<News>>) newsService.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.body() != null) {
                    if (response.body().size() != 0) {
                        //List<News> listOfNews = response.body() is ArrayList<News>
                        ArrayList<News> listOfNews = (ArrayList<News>) response.body();
                        listener.onGetNewsSuccess(listOfNews);
                    } else {
                        listener.onGetNewsNotFound();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listener.onGetNewsFailed();
            }
        });
    }

}
