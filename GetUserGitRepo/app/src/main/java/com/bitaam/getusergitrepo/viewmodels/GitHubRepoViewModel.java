package com.bitaam.getusergitrepo.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bitaam.getusergitrepo.R;
import com.bitaam.getusergitrepo.adapter.GitHubRepoAdapter;
import com.bitaam.getusergitrepo.modals.GitHubRepo;
import com.bitaam.getusergitrepo.repository.GitHubClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GitHubRepoViewModel extends AndroidViewModel {
    private Subscription subscription;

    public GitHubRepoViewModel(@NonNull Application application) {
        super(application);
    }


    public Subscription getStarredRepos(String username, GitHubRepoAdapter gitHubRepoAdapter, Context context) {

        gitHubRepoAdapter.setGitHubRepos(new ArrayList<>());

        subscription = GitHubClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepo>>() {
                    @Override public void onCompleted() {
                        Log.d("viewmodel", "Completed");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("viewmodel", "error occured");
                        Toast.makeText(context, "Username does not exists or user do not have any repos", Toast.LENGTH_SHORT).show();
                    }

                    @Override public void onNext(List<GitHubRepo> gitHubRepos) {
                        Log.d("viewmodel", "Got Repos");
                        if (gitHubRepos.size()==0){
                            Toast.makeText(context, "Username does not exists or user do not have any repos", Toast.LENGTH_SHORT).show();
                        }
                        gitHubRepoAdapter.setGitHubRepos(gitHubRepos);
                    }
                });
        return subscription;
    }

}