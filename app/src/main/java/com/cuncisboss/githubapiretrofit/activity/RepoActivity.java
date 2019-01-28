package com.cuncisboss.githubapiretrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuncisboss.githubapiretrofit.R;
import com.cuncisboss.githubapiretrofit.adapter.RepoAdapter;
import com.cuncisboss.githubapiretrofit.api.ApiClient;
import com.cuncisboss.githubapiretrofit.api.TheGithubApi;
import com.cuncisboss.githubapiretrofit.model.RepoUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvUsername;
    RepoAdapter adapter;
    List<RepoUser> repoUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        getSupportActionBar().setTitle("Repository");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repoUsers = new ArrayList<>();

        tvUsername = findViewById(R.id.tv_username);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String newString = getIntent().getStringExtra("STRING_USER");
        tvUsername.setText("User: " + newString);
        loadData(newString);
    }

    private void loadData(String user) {
        TheGithubApi theGithubApi = ApiClient.getClient().create(TheGithubApi.class);
        Call<List<RepoUser>> call = theGithubApi.getRepoUser(user);
        call.enqueue(new Callback<List<RepoUser>>() {
            @Override
            public void onResponse(Call<List<RepoUser>> call, Response<List<RepoUser>> response) {
                if (response.isSuccessful()) {
                    repoUsers.clear();
                    repoUsers.addAll(response.body());
                    adapter = new RepoAdapter(repoUsers, RepoActivity.this);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RepoActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RepoUser>> call, Throwable t) {
                Toast.makeText(RepoActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}



















