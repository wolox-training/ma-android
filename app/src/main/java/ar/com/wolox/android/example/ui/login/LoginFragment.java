package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private TextView loginTextView;
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
        View view = this.getView();
        loginButton = view.findViewById(R.id.vLoginButton);
        signupButton = view.findViewById(R.id.vSignupButton);
        passwordInput = view.findViewById(R.id.vPasswordInput);
        loginTextView = view.findViewById(R.id.vLoginTextView);
        conditionsTextView = view.findViewById(R.id.vConditionsTextView);
        mailInput = view.findViewById(R.id.vMailInput);
        mailInput.setText(getPresenter().getLastLoggeduser(getActivity()));
        conditionsTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }
    /**
     * set listeners
     */
    @Override
    public void setListeners() {
        signupButton.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivity(intent);

        });

        loginButton.setOnClickListener(v -> {
            if (this.mailAndPasswordInputAreCorrect()) {
                getPresenter().storeUser(mailInput.getText().toString(), passwordInput.getText().toString(), getActivity());
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });
        conditionsTextView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(String.valueOf(R.string.woloxURL)));
            startActivity(browserIntent);
        });
    }


    /** @return
     *   mailAndPasswordInputAreCorrect returns true when the user enters a valid email and a password. Otherwise, it returns false.
     */
    public boolean mailAndPasswordInputAreCorrect() {
        if ((mailInput.getText().toString().matches("")) || (passwordInput.getText().toString().matches(""))) {
            //textViewError.requestFocus();
            //textViewError.setError("Todos los campos son necesarios");
            passwordInput.requestFocus();
            passwordInput.setError(getResources().getString(R.string.login_password_missing));
            mailInput.requestFocus();
            mailInput.setError("Es necesario ingresar mail");
            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(mailInput.getText().toString()).matches()) {
                //mailInput.requestFocus();
                //mailInput.setError("Mail inválido");
                mailInput.requestFocus();
                mailInput.setError("Un ejemplo de formato válido es example@domain.com");
                return false;
            } else {
                return true;
            }
        }
    }
}