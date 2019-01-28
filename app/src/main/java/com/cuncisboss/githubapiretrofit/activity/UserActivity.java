package com.cuncisboss.githubapiretrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuncisboss.githubapiretrofit.R;
import com.cuncisboss.githubapiretrofit.api.ApiClient;
import com.cuncisboss.githubapiretrofit.api.TheGithubApi;
import com.cuncisboss.githubapiretrofit.model.GithubUser;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";

    ImageView imgAvatar;
    TextView tvUsername, tvFollowers, tvFollowing, tvLogin, tvEmail;
    Button btnRepositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        String newString = getIntent().getStringExtra("STRING_I_NEED");

        Log.d(TAG, "onCreate: " + newString);
        loadData(newString);
    }

    private void loadData(String newString) {
        TheGithubApi theGithubApi = ApiClient.getClient().create(TheGithubApi.class);
        Call<GithubUser> call = theGithubApi.getUser(newString);
        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getLogin());
                    GithubUser data = response.body();
                    if (response.body().getName() == null) {
                        tvUsername.setText("Username : No Name Provided");
                    } else {
                        tvUsername.setText("Username : " + data.getName());
                    }

                    tvLogin.setText("Login : " + data.getLogin());
                    tvFollowers.setText("Followers : " + data.getFollowers());
                    tvFollowing.setText("Following : " + data.getFollowing());

                    if (response.body().getEmail() == null) {
                        tvEmail.setText("Email : No Email Provided");
                    } else {
                        tvEmail.setText("Email : " + response.body().getEmail());
                    }

                    Picasso.get().load(data.getAvatar()).into(imgAvatar);

                } else {
                    Toast.makeText(UserActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Toast.makeText(UserActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void initView() {
        imgAvatar = findViewById(R.id.img_avatar);
        tvUsername = findViewById(R.id.tv_username);
        tvFollowers = findViewById(R.id.tv_followers);
        tvFollowing = findViewById(R.id.tv_following);
        tvLogin = findViewById(R.id.tv_login);
        tvEmail = findViewById(R.id.tv_email);
        btnRepositories = findViewById(R.id.btn_repositories);
    }
}
