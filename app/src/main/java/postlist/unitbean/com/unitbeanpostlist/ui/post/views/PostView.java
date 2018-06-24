package postlist.unitbean.com.unitbeanpostlist.ui.post.views;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;

public interface PostView extends BaseView {

    void showComments(List<CommentModel> comments);
    void showProgressBar();
    void hideProgressBar();
}
