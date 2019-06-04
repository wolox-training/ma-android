package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.example.API.APIClient;
import ar.com.wolox.android.example.API.User;
import ar.com.wolox.android.example.network.UserService;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String MAIL_KEY = "mail";
    private static final String PASSWORD_KEY = "password";
    private static final String USER_SESSION_SHARE_PREFERENCE = "user_session";
    private UserService userService;

    @Inject
    LoginPresenter() {
        userService = APIClient.getRetrofitClient().create(UserService.class);
    }

    String getLastLoggeduser(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(MAIL_KEY, null);
    }

    void storeUser(String mail, String password, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MAIL_KEY, mail);
        editor.commit();
    }


    public void getUsers() {
        List<User> users = null;
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    List<User> users = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }

    public void getUserByMail(String mail) {
        List<User> users = null;
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    List<User> users = response.body();
                    if (isUserRegistered(users, mail)) {
                        getView().onGetUserByMailFinished(true);
                    } else {
                        getView().onGetUserByMailFinished(false);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }

    boolean isUserRegistered(List<User> users, String mail) {
        int i = 0;
        while ((i < users.size()) && !(users.get(i).isMyEmail(mail))) {
            i++;
        }
        return i != users.size();

    }
}
