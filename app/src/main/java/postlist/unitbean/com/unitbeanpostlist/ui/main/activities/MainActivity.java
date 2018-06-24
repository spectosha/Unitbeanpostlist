package postlist.unitbean.com.unitbeanpostlist.ui.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.main.adapters.MainPostAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.presenters.MainPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.views.MainView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.activities.PostActivity;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    ProgressBar progressBar;
    Toolbar toolbar;
    RecyclerView recyclerView;
    MainPostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.main_progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter.makeRequestToPosts();
    }

    @Override
    public void showPosts(List<PostModel> list) {
        LinearLayoutManager layout = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layout);
        adapter = new MainPostAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    public void openPost(int position) {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    public void showPostItem(PostModel post) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(PostActivity.POST_ID, post.getId());
        intent.putExtra(PostActivity.TITLE, post.getTitle());
        intent.putExtra(PostActivity.BODY, post.getBody());
        intent.putExtra(PostActivity.DATE, post.getDate());
        startActivity(intent);
    }
}