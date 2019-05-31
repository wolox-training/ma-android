package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
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
        return R.layout.fragment_example;
    }

    @Override
    public void init() {
        View view = this.getView();
        loginButton = view.findViewById(R.id.vLoginButton);
        signupButton = view.findViewById(R.id.vSignupButton);
        passwordInput = view.findViewById(R.id.vPasswordInput);
        loginTextView = view.findViewById(R.id.vLoginTextView);
        mailInput = view.findViewById(R.id.vMailInput);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String mail = sharedPref.getString("Mail", null);
        String password = sharedPref.getString("Password", null);
        mailInput.setText(mail);
        passwordInput.setText(password);
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
                passwordInput.setError("Es necesario ingresar contraseña");
                mailInput.requestFocus();
                mailInput.setError("Es necesario ingresar mail");
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(mailInput.getText().toString()).matches()) {
                    mailInput.requestFocus();
                    mailInput.setError("Un ejemplo de formato válido es example@domain.com");
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