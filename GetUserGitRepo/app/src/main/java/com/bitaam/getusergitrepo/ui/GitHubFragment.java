package com.bitaam.getusergitrepo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.bitaam.getusergitrepo.R;
import com.bitaam.getusergitrepo.adapter.GitHubRepoAdapter;
import com.bitaam.getusergitrepo.viewmodels.GitHubRepoViewModel;

import rx.Subscription;

public class GitHubFragment extends Fragment {

    private GitHubRepoAdapter adapter;
    private Subscription subscription;
    private GitHubRepoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_git_hub, container, false);

        adapter = new GitHubRepoAdapter(getContext());

        viewModel = ViewModelProviders.of(this).get(GitHubRepoViewModel.class);

        final ListView listView = view.findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);
        final EditText editTextUsername = view.findViewById(R.id.edit_text_username);
        final Button buttonSearch = view.findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    subscription = viewModel.getStarredRepos(username,adapter,getContext());
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        super.onDestroy();
    }
}