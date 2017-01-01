package com.mtleung.demoretrofitmvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mtleung.demoretrofitmvc.MainApplication;
import com.mtleung.demoretrofitmvc.PresenterManager;
import com.mtleung.demoretrofitmvc.R;
import com.mtleung.demoretrofitmvc.interfaces.MainInterface.MainViewIntf;
import com.mtleung.demoretrofitmvc.modules.MainActivityModule;
import com.mtleung.demoretrofitmvc.presenter.MainPresenter;
import com.mtleung.demoretrofitmvc.database.SharedPrefHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Marco on 31/12/2016.
 */

public class MainActivity extends AppCompatActivity implements MainViewIntf {
    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_loading) ProgressBar loading;
    @BindView(R.id.main_username) EditText usernameInput;
    @BindView(R.id.main_password) EditText passwordInput;

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            // presenter = new MainPresenter(sharedPrefHelper);
            setupComponent();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    private void setupComponent() {
        MainApplication.get(this)
                .getAppComponent()
                .getMainComponent(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        presenter.bindView(this);
        presenter.checkLastLogin();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void navigatePostActivity() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showUsernameError() {
        Toast.makeText(this, getString(R.string.main_username_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordError() {
        Toast.makeText(this, getString(R.string.main_password_error), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_submit)
    @Override
    public void onLoginClicked() {
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        presenter.validateCredentials(username, password);
    }
}
