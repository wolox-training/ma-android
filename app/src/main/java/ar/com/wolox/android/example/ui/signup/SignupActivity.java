package ar.com.wolox.android.example.ui.signup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ar.com.wolox.android.R;

/**
 * Sign up activity
 */
public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
