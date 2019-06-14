package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.example.network.APIAdapter
import ar.com.wolox.android.example.utils.UserSession
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class ExamplePresenterTest {

    private lateinit var mExampleView: ILoginView
    private lateinit var mExamplePresenter: LoginPresenter
    private lateinit var mUserSession: UserSession

    @Before
    fun createInstances() {
        mExampleView = mock(ILoginView::class.java)
        mUserSession = mock(UserSession::class.java)
        mExamplePresenter = LoginPresenter(mock(APIAdapter::class.java))
    }

    @Test
    fun usernameIsStored() {
        mExamplePresenter.attachView(mExampleView)
    }

    @Test
    fun storeUsernameUpdatesView() {
        mExamplePresenter.attachView(mExampleView)
    }
}
