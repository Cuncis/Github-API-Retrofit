package com.cuncisboss.githubapiretrofit.api;

import com.cuncisboss.githubapiretrofit.model.GithubUser;
import com.cuncisboss.githubapiretrofit.model.RepoUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheGithubApi {
    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<RepoUser>> getRepoUser(@Path("user") String user);
}
