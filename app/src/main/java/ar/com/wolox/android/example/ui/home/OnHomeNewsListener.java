package ar.com.wolox.android.example.ui.home;

import java.util.ArrayList;
/**
 *  Home listener
 */
public interface OnHomeNewsListener {
    void onGetNewsSuccess(ArrayList<News> body);
    void onGetNewsNotFound();
    void onGetNewsFailed();
}
