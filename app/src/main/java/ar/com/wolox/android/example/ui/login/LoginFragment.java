package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.signup.SignupActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Login Fragment. Displays sign up button, log in button and both mail and password fields that the user fills.
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    private Button loginButton;
    private Button signupButton;
    private EditText passwordInput;
    private EditText mailInput;
    private TextView conditionsTextView;

    @Inject
    LoginFragment() {

    }

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        if ((getPresenter().getLastLoggeduser(getActivity()) != null)) {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
        View view = this.getView();
        loginButton = view.findViewById(R.id.vLoginButton);
        signupButton = view.findViewById(R.id.vSignupButton);
        passwordInput = view.findViewById(R.id.vPasswordInput);
        conditionsTextView = view.findViewById(R.id.vConditionsTextView);
        mailInput = view.findViewById(R.id.vMailInput);
        mailInput.setText(getPresenter().getLastLoggeduser(getActivity()));
        conditionsTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mailInput.setText(getPresenter().getLastLoggeduser(getActivity()));
    }

    /**
     * set listeners
     */
    @Override
    public void setListeners() {
        signupButton.setOnClickListener(v -> {
            this.clearSetErrorMessages();
            if (this.mailIsCorrect()) {
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(v -> {
            this.clearSetErrorMessages();
            if (this.mailAndPasswordInputAreCorrect()) {
                getPresenter().getUserByMail(mailInput.getText().toString(), passwordInput.getText().toString(), getActivity());
            }
        });
    }

    public void clearSetErrorMessages() {
        mailInput.setError(null);
        passwordInput.setError(null);
    }

    /**
     * @return true when the user enters a valid email and a password. Otherwise, it returns false.
     */
    private boolean mailAndPasswordInputAreCorrect() {
        if (this.mailIsCorrect()) {
            if (passwordInput.getText().toString().isEmpty()) {
                passwordInput.setError(getResources().getString(R.string.password_missing));
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    /**
     * @return true when the user enters a valid email. Otherwise, it returns false.
     */
    private boolean mailIsCorrect() {
        if (mailInput.getText().toString().isEmpty()) {
            mailInput.setError(getResources().getString(R.string.mail_missing));
            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(mailInput.getText().toString()).matches()) {
                mailInput.setError(getResources().getString(R.string.mail_example));
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void onGetUserByMailFinished(Boolean userIsFound) {
        if (userIsFound) {
            getPresenter().storeUser(mailInput.getText().toString(), getActivity());
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        } else {
            Toast toast;
            toast = Toast.makeText(getContext(), getResources().getString(R.string.mail_or_password_are_incorrect), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        passwordInput.setText("");
    }

    public void cellphoneIsDisconnecteed() {
        Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.disconnected_cellphone), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void failedApiConnection() {
        Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.failed_api_connection), Toast.LENGTH_SHORT);
        toast.show();
    }
}