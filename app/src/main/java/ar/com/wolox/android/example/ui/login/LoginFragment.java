package ar.com.wolox.android.example.ui.login;

import android.graphics.Color;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Login Fragment. Displays sign up button, log in button and both mail and password fields that the user fills.
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    private Button loginButton;
    private Button signupButton;
    private EditText passwordInput;
    private EditText mailInput;
    private TextView loginTextView;

    @Inject
    LoginFragment() {

    }

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        View view = this.getView();
        loginButton = view.findViewById(R.id.vLoginButton);
        signupButton = view.findViewById(R.id.vSignupButton);
        passwordInput = view.findViewById(R.id.vPasswordInput);
        loginTextView = view.findViewById(R.id.vLoginTextView);
        mailInput = view.findViewById(R.id.vMailInput);
        mailInput.setText(getPresenter().getLastLoggeduser(getActivity()));
    }
    /**
     * set listeners
     */
    @Override
    public void setListeners() {
        //sacar esto
        loginButton.setOnClickListener(v -> {
            if ((mailInput.getText().toString().matches("")) || (passwordInput.getText().toString().matches(""))) {
                passwordInput.requestFocus();
                passwordInput.setError(getResources().getString(R.string.login_password_missing));
                mailInput.requestFocus();
                mailInput.setError(getResources().getString(R.string.login_mail_missing));
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(mailInput.getText().toString()).matches()) {
                    mailInput.requestFocus();
                    mailInput.setError(getResources().getString((R.string.login_mail_example)));
                } else {
                    getPresenter().storeUser(mailInput.getText().toString(), passwordInput.getText().toString(), getActivity());
                }
            }
        });
    }

    //TODO: Take out loginTextView and onSignUpFinished method when sigup activity is developed
    @Override
    public void onSignUpFinished(String message) {
        loginTextView.setTextColor(Color.GREEN);
        loginTextView.setText(message);
    }
}