package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import ar.com.wolox.wolmo.core.presenter.BasePresenter;

class LoginPresenter extends BasePresenter<ILoginView> {

    public static final String CONSTANT_KEY_MAIL = "Mail";
    public static final String CONSTANT_KEY_PASSWORD = "Password";
    @Inject
    LoginPresenter() {

    }

    void storeUser(String mail, String password, Activity loginActivity) {
        SharedPreferences sharedPref = loginActivity.getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CONSTANT_KEY_MAIL, mail);
        editor.putString(CONSTANT_KEY_PASSWORD, password);
        editor.commit();
        getView().onSignUpFinished("Estas registrado");
    }

}
