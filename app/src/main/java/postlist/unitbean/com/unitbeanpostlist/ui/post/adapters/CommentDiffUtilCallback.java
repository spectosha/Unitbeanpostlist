package postlist.unitbean.com.unitbeanpostlist.ui.post.adapters;

import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public class CommentDiffUtilCallback extends DiffUtil.Callback {

    private List<CommentModel> oldList;
    private List<CommentModel> newList;

    public CommentDiffUtilCallback(List<CommentModel> oldList, List<CommentModel> newList) {
        this.oldList = oldList;
        this.newList = newList;

        Log.i(BaseView.LOG_TAG, oldList.toString());
        Log.i(BaseView.LOG_TAG, newList.toString());
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        CommentModel oldPost = oldList.get(oldItemPosition);
        CommentModel newPost = newList.get(newItemPosition);
        return oldPost.getId() == newPost.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        CommentModel oldPost = oldList.get(oldItemPosition);
        CommentModel newPost = newList.get(newItemPosition);
        return oldPost.getBody().equals(newPost.getBody())
                && oldPost.getName().equals(newPost.getName());
    }
}
