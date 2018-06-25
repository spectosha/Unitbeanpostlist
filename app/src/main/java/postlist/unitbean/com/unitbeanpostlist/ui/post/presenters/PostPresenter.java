package postlist.unitbean.com.unitbeanpostlist.ui.post.presenters;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.views.PostView;

@InjectViewState
public class PostPresenter extends BasePresenter<PostView> {

    private List<CommentModel> list;

    public void makeRequestToComments(int postId){

        Disposable disposable = coreServices
                .getApiService()
                .getApi()
                .getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(r -> getViewState().showProgressBar())
                .doFinally(() -> getViewState().hideProgressBar())
                .doOnError(e -> getViewState().log("Error: " + e.getMessage()))
                .subscribe(result -> {
                    list = result;
                    getViewState().showComments(list);
                }, throwable -> {
                    getViewState().log("Нет соединения с интернетом!");
                });

        subscriptions.add(disposable);
    }

    public List<CommentModel> getList(){
        return list;
    }
}
