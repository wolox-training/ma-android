package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import ar.com.wolox.wolmo.core.presenter.BasePresenter;

class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String MAIL_KEY = "mail";
    private static final String PASSWORD_KEY = "password";
    private static final String USER_SESSION_SHARE_PREFERENCE = "user_session";

    @Inject
    LoginPresenter() {

    }

    String getLastLoggeduser(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(MAIL_KEY, null);
    }

    void storeUser(String mail, String password, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(USER_SESSION_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MAIL_KEY, mail);
        editor.putString(PASSWORD_KEY, password);
        editor.commit();
    }

}
