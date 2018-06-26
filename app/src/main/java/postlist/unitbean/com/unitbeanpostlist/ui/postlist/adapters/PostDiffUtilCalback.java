package postlist.unitbean.com.unitbeanpostlist.ui.postlist.adapters;

import android.support.v7.util.DiffUtil;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public class PostDiffUtilCalback extends DiffUtil.Callback {

    private List<PostModel> oldList;
    private List<PostModel> newList;

    public PostDiffUtilCalback(List<PostModel> oldList, List<PostModel> newList){
        this.oldList = oldList;
        this.newList = newList;
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
        PostModel oldPost = oldList.get(oldItemPosition);
        PostModel newPost = newList.get(newItemPosition);
        return oldPost.getId() == newPost.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PostModel oldPost = oldList.get(oldItemPosition);
        PostModel newPost = newList.get(newItemPosition);
        return oldPost.getTitle().equals(newPost.getTitle())
                && oldPost.getBody().equals(newPost.getBody());
    }
}
