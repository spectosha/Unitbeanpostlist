package postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.presenters;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.views.BottomSheetPostsView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostEnum;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

@InjectViewState
public class BottomSheetPostsPresenter extends BasePresenter<BottomSheetPostsView> {


    private PostModel header;
    private List<PostModel> listPosts = new ArrayList<>();
    private List<CommentModel> listComments = new ArrayList<>();

    public void makeRequestToPosts() {

        Disposable disposable = coreServices
                .getApiService()
                .getApi()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(r -> getViewState().showProgressBar())
                .subscribe(response -> {
                    listPosts = response;
                    getViewState().showPosts(listPosts);
                    getViewState().hideProgressBar();
                }, throwable -> {
                    getViewState().log("Отсутствует интернет соединение");
                });

        subscriptions.add(disposable);
    }

    public void makeRequestToComments(int postId){
        Disposable disposable = coreServices
                .getApiService()
                .getApi()
                .getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> getViewState().log("Error: " + e.getMessage()))
                .subscribe(result -> {
                    listComments = result;
                    getViewState().showComments(listComments);
                }, throwable -> {
                    getViewState().log("Нет соединения с интернетом!");
                });

        subscriptions.add(disposable);
    }

    public List<PostModel> getListPosts() {
        return listPosts;
    }

    public List<CommentModel> getListComments() {
        return listComments;
    }

    public PostModel getHeader() {
        return header;
    }

    public void setHeader(PostModel header) {
        this.header = header;
    }
}
