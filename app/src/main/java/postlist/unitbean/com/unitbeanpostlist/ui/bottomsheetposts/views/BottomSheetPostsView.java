package postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.views;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;


public interface BottomSheetPostsView extends BaseView {
    void showComments(List<CommentModel> comments);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPost(PostModel post, List<CommentModel> comments);
    void showPosts(List<PostModel> list);
    void showProgressBar();
    void hideProgressBar();
}
