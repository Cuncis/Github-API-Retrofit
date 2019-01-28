package com.cuncisboss.githubapiretrofit.model;

import com.google.gson.annotations.SerializedName;

public class RepoUser {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    public RepoUser() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
}
