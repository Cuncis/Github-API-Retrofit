package com.cuncisboss.githubapiretrofit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuncisboss.githubapiretrofit.R;
import com.cuncisboss.githubapiretrofit.model.GithubUser;
import com.cuncisboss.githubapiretrofit.model.RepoUser;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    List<RepoUser> users;
    Context context;

    public RepoAdapter(List<RepoUser> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false);
        return new RepoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int i) {
        holder.tvRepoName.setText("Name: " + users.get(i).getName());
        holder.tvRepoDesc.setText("Descriptions: " + users.get(i).getDescription());
        holder.tvRepoLang.setText("Language: " + users.get(i).getLanguage());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView tvRepoName, tvRepoDesc, tvRepoLang;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRepoName = itemView.findViewById(R.id.tv_repoName);
            tvRepoDesc = itemView.findViewById(R.id.tv_repoDescriptions);
            tvRepoLang = itemView.findViewById(R.id.tv_repoLanguage);
        }
    }
}























