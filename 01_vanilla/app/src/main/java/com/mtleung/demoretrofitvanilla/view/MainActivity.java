package com.mtleung.demoretrofitvanilla.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mtleung.demoretrofitvanilla.MainApplication;
import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.utilities.LoginListener;
import com.mtleung.demoretrofitvanilla.utilities.LoginValidator;
import com.mtleung.demoretrofitvanilla.utilities.SharedPrefHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_loading) ProgressBar loading;
    @BindView(R.id.main_username) EditText usernameInput;
    @BindView(R.id.main_password) EditText passwordInput;

    Context context;
    SharedPrefHelper sharedPrefHelper;
    LoginValidator loginValidator;
    LoginResultListener loginResultListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up UI
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Check if username has already been set
        MainApplication app = (MainApplication) getApplication();
        sharedPrefHelper = app.getSharedPrefHelper();
        String username = sharedPrefHelper.getPref(SharedPrefHelper.USERNAME);

        if (username != null) {
            // If username is set, go to post activity
            navigatePostActivity();
        }

        loginValidator = new LoginValidator();
        loginResultListener = new LoginResultListener();
        context = this;
    }

    @OnClick(R.id.main_submit)
    public void onSubmitClicked() {
        loading.setVisibility(View.VISIBLE);
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        loginValidator.validate(username, password, loginResultListener);
    }

    private void navigatePostActivity() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
        finish();
    }

    private class LoginResultListener implements LoginListener {

        @Override
        public void onUsernameError() {
            loading.setVisibility(View.INVISIBLE);
            Toast.makeText(context, getString(R.string.main_username_error), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPasswordError() {
            loading.setVisibility(View.INVISIBLE);
            Toast.makeText(context, getString(R.string.main_password_error), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess() {
            loading.setVisibility(View.INVISIBLE);
            String username = usernameInput.getText().toString().trim();
            sharedPrefHelper.setPref(SharedPrefHelper.USERNAME, username);
            navigatePostActivity();
        }
    }
}
