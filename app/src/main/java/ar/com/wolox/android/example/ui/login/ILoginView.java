package ar.com.wolox.android.example.ui.login;
/**
 * Login View.
 */
public interface ILoginView {
    //If userFound value is true, there is an user already signed up with the email rpovided, otherwise, there is not
    void onGetUserByMailFinished(Boolean userFound);
}