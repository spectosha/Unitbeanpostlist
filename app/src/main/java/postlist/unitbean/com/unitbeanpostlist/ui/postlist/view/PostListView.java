package postlist.unitbean.com.unitbeanpostlist.ui.postlist.view;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public interface PostListView extends BaseView {
    void showPosts(List<PostModel> list);
    void showProgressBar();
    void hideProgressBar();
}
