package com.mtleung.demoretrofitvanilla.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.mtleung.demoretrofitvanilla.MainApplication;
import com.mtleung.demoretrofitvanilla.R;
import com.mtleung.demoretrofitvanilla.adapter.PostAdapter;
import com.mtleung.demoretrofitvanilla.api.JSONPlaceholderService;
import com.mtleung.demoretrofitvanilla.model.Post;
import com.mtleung.demoretrofitvanilla.utilities.SharedPrefHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_username) EditText usernameInput;

    SharedPrefHelper sharedPrefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up UI
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        // Check if username has already been set
        MainApplication app = (MainApplication) getApplication();
        sharedPrefHelper = app.getSharedPrefHelper();
        String username = sharedPrefHelper.getPref(SharedPrefHelper.USERNAME);

        if (username != null) {
            // If username is set, go to post activity
            navigatePostActivity();
        }
    }

    @OnClick(R.id.main_submit)
    public void onSubmitClicked() {
        String input = usernameInput.getText().toString().trim();
        if (input.length() == 0) {
            return;
        }
        sharedPrefHelper.setPref(SharedPrefHelper.USERNAME, input);
        navigatePostActivity();
    }

    private void navigatePostActivity() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
        finish();
    }
}
