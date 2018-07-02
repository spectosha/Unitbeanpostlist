package postlist.unitbean.com.unitbeanpostlist.ui.post.presenters;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostEnum;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.views.PostView;

@InjectViewState
public class PostPresenter extends BasePresenter<PostView> {

    private PostModel header;
    private List<CommentModel> list = new ArrayList<>();
    private List<PostEnum> listOfTypes = new ArrayList<>();

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
                    setListOfTypes();
                    getViewState().showComments(list);
                }, throwable -> {
                    getViewState().log("Нет соединения с интернетом!");
                });

        subscriptions.add(disposable);
    }

    public PostModel getHeader() {
        return header;
    }

    public void setHeader(PostModel header) {
        this.header = header;
        setListOfTypes();
    }

    public List<CommentModel> getList(){
        return list;
    }

    private void setListOfTypes(){
        listOfTypes.clear();
        listOfTypes.add(header);
        listOfTypes.addAll(list);
    }

}
