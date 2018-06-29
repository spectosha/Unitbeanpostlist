package postlist.unitbean.com.unitbeanpostlist.ui.postlist.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.adapter.BaseAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.adapters.PostListAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.adapters.PostDiffUtilCalback;
import postlist.unitbean.com.unitbeanpostlist.ui.post.activities.PostActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.presenters.PostListPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.view.PostListView;

public class PostListFragment extends BaseFragment implements PostListView, BaseAdapter.OnItemClickListener {

    public static final String TITLE = "title";

    @InjectPresenter
    PostListPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private PostListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);

        progressBar = v.findViewById(R.id.post_list_progress_bar);
        recyclerView = v.findViewById(R.id.post_list_recycler_view);
        adapter = new PostListAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        presenter.makeRequestToPosts();
        return v;
    }

    @Override
    public void showPosts(List<PostModel> newPosts) {
        PostDiffUtilCalback postDiffUtil = new PostDiffUtilCalback(adapter.getList(), newPosts);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffUtil);
        adapter.setList(newPosts);
        diffResult.dispatchUpdatesTo(adapter);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    public void showPostItem(PostModel post) {
        Intent intent = new Intent(activity, PostActivity.class);
        intent.putExtra(PostActivity.POST_ID, post.getId());
        intent.putExtra(PostActivity.TITLE, post.getTitle());
        intent.putExtra(PostActivity.BODY, post.getBody());
        intent.putExtra(PostActivity.DATE, post.getDate());
        startActivity(intent);
    }

    @Override
    public void onClick(View view, int position) {
        PostModel post = adapter.getList().get(position);
        showPostItem(post);
    }

}
