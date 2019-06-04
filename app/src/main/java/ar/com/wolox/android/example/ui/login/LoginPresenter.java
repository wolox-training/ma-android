package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

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

    void storeUser(String mail, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MAIL_KEY, mail);
        editor.commit();
    }

    public boolean checkInternetConnection(Activity activity) {
        ConnectivityManager con = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (con.getActiveNetworkInfo() != null && con.getActiveNetworkInfo().isAvailable() && con.getActiveNetworkInfo().isConnected());
    }

    public void getUserByMail(String mail, String password, Activity activity) {
        if (checkInternetConnection(activity)) {
            Call<List<User>> call = userService.getUserByMail(mail, password);
            ProgressDialog pd = new ProgressDialog(activity);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setTitle("Conectandose a la API");
            pd.setMessage("Cargando ... ");
            pd.show();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.body() != null) {
                        if (response.body().size() != 0) {
                            getView().onGetUserByMailFinished(true);
                        } else {
                            getView().onGetUserByMailFinished(false);
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    pd.dismiss();
                }
            });
        } else {
            getView().cellphoneIsDisconnecteed();
        }
    }

}
