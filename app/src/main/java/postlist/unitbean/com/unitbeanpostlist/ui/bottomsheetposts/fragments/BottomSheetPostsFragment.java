package postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.adapter.BaseAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.presenters.BottomSheetPostsPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.views.BottomSheetPostsView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.CommentDiffUtilCallback;
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.PostAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.adapters.PostDiffUtilCalback;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.adapters.PostListAdapter;

public class BottomSheetPostsFragment extends BaseFragment implements BottomSheetPostsView, BaseAdapter.OnItemClickListener {

    @InjectPresenter
    BottomSheetPostsPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerViewMain;
    private PostListAdapter adapter;

    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView recyclerViewBottomSheet;
    private PostAdapter bottomSheetAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        progressBar = v.findViewById(R.id.bs_progress_bar_main);
        recyclerViewMain = v.findViewById(R.id.bs_rv_main);
        adapter = new PostListAdapter();
        adapter.setOnItemClickListener(this);
        recyclerViewMain.setAdapter(adapter);

        bottomSheetBehavior = BottomSheetBehavior.from(v.findViewById(R.id.bottom_sheet));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        recyclerViewBottomSheet = v.findViewById(R.id.bottom_sheet_rv);
        bottomSheetAdapter = new PostAdapter();
        recyclerViewBottomSheet.setAdapter(bottomSheetAdapter);
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
        bottomSheetAdapter.setHeader(post);
        bottomSheetAdapter.notifyDataSetChanged();
    }

    @Override
    public void showComments(List<CommentModel> newComments) {
        //CommentDiffUtilCallback commentsDiffUtil = new CommentDiffUtilCallback(bottomSheetAdapter.getComments(), newComments);
        //DiffUtil.DiffResult commentsDiffResult = DiffUtil.calculateDiff(commentsDiffUtil);
        bottomSheetAdapter.setComments(presenter.getListComments());
        //commentsDiffResult.dispatchUpdatesTo(bottomSheetAdapter);
        //recyclerViewBottomSheet.smoothScrollToPosition(0);
        bottomSheetAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPost(PostModel post, List<CommentModel> newComments) {
        bottomSheetAdapter.setHeader(post);
        bottomSheetAdapter.setComments(presenter.getListComments());
        bottomSheetAdapter.notifyDataSetChanged();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onClick(View view, int position) {
        PostModel post = adapter.getList().get(position);
        presenter.makeRequestToComments(post.getId());
    }
}
