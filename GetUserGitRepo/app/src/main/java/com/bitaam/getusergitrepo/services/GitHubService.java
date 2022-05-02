package com.bitaam.getusergitrepo.services;

import com.bitaam.getusergitrepo.modals.GitHubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubService {
    @GET("users/{user}/repos")
    Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);
    //@GET("users/{user}/starred")
}
