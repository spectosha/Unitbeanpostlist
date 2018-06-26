package postlist.unitbean.com.unitbeanpostlist.ui.post.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.CommentDiffUtilCallback;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.PostAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.presenters.PostPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.views.PostView;

public class PostActivity extends BaseActivity implements PostView{

    public static final String POST_ID = "post_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String DATE = "date";

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private ProgressBar progressBar;

    private PostModel postModel;

    @InjectPresenter
    PostPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        Intent intent = getIntent();
        int postId = intent.getExtras().getInt(POST_ID, 1);
        String title = intent.getExtras().getString(TITLE, "undefined");
        String body = intent.getExtras().getString(BODY, "undefined");
        postModel = new PostModel();
        postModel.setId(postId);
        postModel.setTitle(title);
        postModel.setBody(body);

        recyclerView = findViewById(R.id.post_item_comments);
        postAdapter = new PostAdapter(postModel);
        recyclerView.setAdapter(postAdapter);

        progressBar = findViewById(R.id.post_item_progress_bar);

        presenter.makeRequestToComments(postId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showComments(List<CommentModel> newComments) {
        CommentDiffUtilCallback commentsDiffUtil = new CommentDiffUtilCallback(postAdapter.getComments(), newComments);
        DiffUtil.DiffResult commentsDiffResult = DiffUtil.calculateDiff(commentsDiffUtil);
        postAdapter.setComments(newComments);
        commentsDiffResult.dispatchUpdatesTo(postAdapter);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
