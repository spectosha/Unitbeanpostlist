package postlist.unitbean.com.unitbeanpostlist.ui.post.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.CommentAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.presenters.PostPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.views.PostView;

public class PostActivity extends BaseActivity implements PostView, View.OnClickListener{

    public static final String POST_ID = "post_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String DATE = "date";

    RecyclerView commentList;
    Toolbar toolbar;
    ProgressBar progressBar;

    TextView title;
    TextView body;
    TextView date;

    @InjectPresenter
    PostPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle(R.string.post_action_bar_title);
        toolbar.setNavigationOnClickListener(this);

        progressBar = findViewById(R.id.post_item_progress_bar);

        title = findViewById(R.id.post_item_title);
        body = findViewById(R.id.post_item_body);
        date = findViewById(R.id.post_item_date);

        Intent intent = getIntent();
        int postId = intent.getExtras().getInt(POST_ID, 1);
        title.setText(intent.getExtras().getString(TITLE, "undefined"));
        body.setText(intent.getExtras().getString(BODY, "undefined"));
        date.setText(intent.getExtras().getString(DATE, "undefined"));

        presenter.makeRequestToComments(postId);
    }

    @Override
    public void showComments(List<CommentModel> comments) {

        //TODO Не уверен в эстетических качествах такого способа запрещать прокрутку. Может мне стоит попробовать использовать другой View вместо recycler?
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            //Запрещаем вертикальную прокрутку recycle view
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        commentList = findViewById(R.id.post_item_comments);

        commentList.setLayoutManager(linearLayoutManager);
        CommentAdapter commentAdapter = new CommentAdapter(comments);
        commentList.setAdapter(commentAdapter);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
