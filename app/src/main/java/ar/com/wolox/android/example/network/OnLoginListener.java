package ar.com.wolox.android.example.network;

/**
 * Implements when need a callback from the {@link APIAdapter}
 */
public interface OnLoginListener {
    void onLoginSuccess();
    void onLoginUserNotFound();
    void onLoginFail();
}
