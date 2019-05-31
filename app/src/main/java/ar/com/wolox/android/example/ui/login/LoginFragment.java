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
 * comment
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
        //AGREGUE ESTOOO
        loginButton = view.findViewById(R.id.vLoginButton);
        signupButton = view.findViewById(R.id.vSignupButton);
        passwordInput = view.findViewById(R.id.vPasswordInput);
        loginTextView = view.findViewById(R.id.vLoginTextView);
        mailInput = view.findViewById(R.id.vMailInput);
        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        /*SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        */
        String mail = sharedPref.getString("Mail", null);
        String password = sharedPref.getString("Password", null);
        //mailInput.setText(sharedPref.getString(mailKey));
        mailInput.setText(mail);
        passwordInput.setText(password);
        //passwordInput.setText(sharedPref.getString("Password"));

    }

    /**
     * comment
     */
    @Override
    public void setListeners() {
        //sacar esto
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getPresenter().storeUsername(usernameInput.getText().toString());
                if ((mailInput.getText().toString().matches("")) || (passwordInput.getText().toString().matches(""))) {
                    //textViewError.requestFocus();
                    //textViewError.setError("Todos los campos son necesarios");
                    passwordInput.requestFocus();
                    passwordInput.setError("Es necesario ingresar contraseña");
                    mailInput.requestFocus();
                    mailInput.setError("Es necesario ingresar mail");
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(mailInput.getText().toString()).matches()) {
                        //mailInput.requestFocus();
                        //mailInput.setError("Mail inválido");
                        mailInput.requestFocus();
                        mailInput.setError("Un ejemplo de formato válido es example@domain.com");
                    } else {
                        getPresenter().storeUser(mailInput.getText().toString(), passwordInput.getText().toString(), getActivity());
                    }
                }
            }
        });
        /*
        signupButton.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!usernameInput.getText().toString().matches("")) {
                    loginButton.setEnabled(true);
                }

            }
        });

        */
    }

    @Override
    public void onSignUpFinished(String message) {
        loginTextView.setTextColor(Color.GREEN);
        loginTextView.setText(message);
    }
}

/*
class LoginFragment : WolmoFragment<LoginPresenter>(), IExampleView {

    override fun layout(): Int = R.layout.fragment_example

    override fun init() {
        vLoginButton.isEnabled = false
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            presenter.storeUsername(vUsernameInput.text.toString())
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewPagerActivity::class.java)
        startActivity(intent)
    }

     private Button loginButton;
    private EditText usernameInput;
    private LoginPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        usernameInput = (EditText) findViewById(R.id.vUsernameInput);
        loginButton = (Button) findViewById(R.id.vLoginButton);
        loginButton.isEnabled = false;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //falta inicializar a presenter (no se si con  presenter = new LoginPresenter() alcanza)
                presenter.storeUsername(usernameInput.text.toString());
            }
        });
        usernameInput.addTextChangedListener(new TextWatcher() {
        @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!usernameInput.matches("")){
                    loginButton.isEnabled;
                }
            }
        });
    }
}

    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginButton = (Button) findViewById(R.id.vLoginButton);

    }

    public View onCreateView() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view =   inflater.inflate(R.layout.fragment_example,container,false);
        ViewGroup container = (ViewGroup) view.findViewById(android.R.id.content);
        loginButton = (Button) view.findViewById(R.id.vLoginButton);
        usernameInput = (EditText) view.findViewById(R.id.vUsernameInput);
        return  inflater.inflate(R.layout.fragment_example,container,false);

    }

    */
