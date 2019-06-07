package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ar.com.wolox.android.example.model.APIClient;
import ar.com.wolox.android.example.network.APIAdapter;
import ar.com.wolox.android.example.network.OnLoginListener;
import ar.com.wolox.android.example.network.UserService;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 * Login Presenter
 */
public class LoginPresenter extends BasePresenter<ILoginView> implements OnLoginListener {

    private static final String MAIL_KEY = "mail";
    private static final String USER_SESSION_SHARE_PREFERENCE = "user_session";
    private UserService userService;
    private APIAdapter apiAdapter;

    @Inject
    LoginPresenter(APIAdapter apiAdapter) {
        this.apiAdapter = apiAdapter;
        userService = APIClient.getRetrofitClient().create(UserService.class);
    }

    String getLastLoggeduser(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        String mail = sharedPref.getString(MAIL_KEY, null);
        return mail;
    }

    void storeUser(String mail, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MAIL_KEY, mail);
        editor.commit();
    }

    public boolean mailIsCorrect(String mail) {
        if (mail.isEmpty()) {
            return false;
        } else {
            String emailRegEx;
            Pattern pattern;
            emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
            pattern = Pattern.compile(emailRegEx);
            return pattern.matcher(mail).matches();
        }
    }

    public void dismissLoading() {
        getView().dismissLoading();
    }

    public void getUserByMail(String mail, String password) {
        if (mailIsCorrect(mail)) {
            apiAdapter.getUserById(mail, password, this);
            getView().showLoading();
        }
    }

    @Override
    public void onLoginSuccess() {
        dismissLoading();
        getView().onGetUserByMailFinished(true);
    }

    @Override
    public void onLoginUserNotFound() {
        dismissLoading();
        getView().onGetUserByMailFinished(false);
    }

    @Override
    public void onLoginFail() {
        dismissLoading();
        getView().failedApiConnection();
    }
}
