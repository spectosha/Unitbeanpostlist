package postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.views;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public interface BottomSheetPostsView extends BaseView {
    void showComments(List<CommentModel> comments);
    void showPosts(List<PostModel> list);
    void showProgressBar();
    void hideProgressBar();
}
