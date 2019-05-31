package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import ar.com.wolox.wolmo.core.presenter.BasePresenter;

class LoginPresenter extends BasePresenter<ILoginView> {

    /*UserSession mUserSession;
    @Inject
    LoginPresenter(UserSession mUserSession) {
        this.mUserSession = mUserSession;
    }
    */

    @Inject
    LoginPresenter() {

    }

    void storeUsername(String text) {

    }

    // AGREGUEE
    void storeUser(String mail, String password, Activity loginActivity) {
        CharSequence error = null;
        //SharedPreferences sharedPref = getDefaultSharedPreferences(Context.MODE_PRIVATE);
        //SharedPreferences sharedPref = loginActivity.getSharedPreferences(Context.MODE_PRIVATE);
        //SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(loginActivity.getApplicationContext());
        /* SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Mail", mail);
        editor.putString("Password", password);
        editor.commit();
        getView().onSignUpFinished("Estas registrado");
        */
        SharedPreferences sharedPref = loginActivity.getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Mail", mail);
        editor.putString("Password", password);
        editor.commit();
        getView().onSignUpFinished("Estas registrado");
    }

    void signupUser(String mail, String password) {

    }

}

/*  fun storeUsername(text: String) {
    }

class ExamplePresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<IExampleView>() {

@Inject constructor( UserSession mUserSession )

    fun storeUsername(text: String) {
        mUserSession.username = text
        view.onUsernameSaved()
    }
}

    */
