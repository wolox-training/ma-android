package ar.com.wolox.android.example.ui.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.wolox.android.example.network.APIAdapter;
import ar.com.wolox.android.example.network.OnLoginListener;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    @Mock
    private ILoginView loginView;
    @Mock
    private APIAdapter apiAdapter;
    private LoginFragment loginFragment;

    private final String INVALID_EMAIL =  "susanexample.com";
    private final String VALID_EMAIL =  "susan.stevens38@example.com";
    private final String INVALID_PASSWORD = "1234";

    @Before
    public void setupTestMailIsEmpty() {
        MockitoAnnotations.initMocks(this);
        loginFragment = new LoginFragment();
        loginPresenter = new LoginPresenter(apiAdapter);
        loginPresenter.attachView(loginView);
    }

    @Test
    public void testMailIsEmpty() {
        assertFalse(loginPresenter.mailIsCorrect(""));
    }

    @Test
    public void testMailIsInvalid() {
        assertFalse(loginPresenter.mailIsCorrect(INVALID_EMAIL));
    }

    @Test
    public void testMailIsValid() {
        assertTrue(loginPresenter.mailIsCorrect(VALID_EMAIL));
    }

    @Test
    public void testAPIShowDialog() {
        loginPresenter.getUserByMail(VALID_EMAIL,"");
        verify(loginView, times(1)).showLoading();
    }

    @Test
    public void testAPIOnSuccess() {
        doAnswer(invocation -> {
            ((OnLoginListener) invocation.getArguments()[2]).onLoginSuccess();
            return invocation;
        }).when(apiAdapter).getUserById(anyString(), anyString(), any());
        loginPresenter.getUserByMail(VALID_EMAIL,"password");
        verify(loginView, times(1)).onGetUserByMailFinished(true);
    }

    @Test
    public void testAPIOnFailed() {
        doAnswer(invocation -> {
            ((OnLoginListener) invocation.getArguments()[2]).onLoginFail();
            return invocation;
        }).when(apiAdapter).getUserById(anyString(), anyString(), any());
        loginPresenter.getUserByMail(VALID_EMAIL,"password");
        verify(loginView, times(1)).failedApiConnection();
    }

    @Test
    public void testAPIOnUserNotFound() {
        doAnswer(invocation -> {
            ((OnLoginListener) invocation.getArguments()[2]).onLoginUserNotFound();
            return invocation;
        }).when(apiAdapter).getUserById(anyString(), anyString(), any());
        loginPresenter.getUserByMail(VALID_EMAIL,"password");
        verify(loginView, times(1)).onGetUserByMailFinished(false);
    }

}
