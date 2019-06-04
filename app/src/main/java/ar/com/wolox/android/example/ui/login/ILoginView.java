package ar.com.wolox.android.example.ui.login;
/**
 * Login View.
 */
public interface ILoginView {
    /*If userFound value is true, there is an user already signed up with the email rpovided, otherwise, there is not
        If password value is true, then the password entered is correct. Otherwise, this value is false.
    */
    void onGetUserByMailFinished(Boolean userFound);
    void cellphoneIsDisconnecteed();
}