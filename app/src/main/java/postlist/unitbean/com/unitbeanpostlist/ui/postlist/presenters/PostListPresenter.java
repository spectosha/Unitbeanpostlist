package postlist.unitbean.com.unitbeanpostlist.ui.postlist.presenters;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.PostEntity;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.view.PostListView;

@InjectViewState
public class PostListPresenter extends BasePresenter<PostListView> {

    private List<PostModel> list = new ArrayList<>();

    public void makeRequestToPosts() {

        Disposable disposable = coreServices
                .getApiService()
                .getApi()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(r -> getViewState().showProgressBar())
                .subscribe(response -> {
                    list = response;
                    getViewState().showPosts(list);
                    getViewState().hideProgressBar();
                    insertAllPosts(list);
                }, throwable -> {
                    getPostsFromDb();
                });

        subscriptions.add(disposable);
    }

    private void insertAllPosts(List<PostModel> insertListModel){
        //Cast to PostEntity
        List<PostEntity> insertListEntity = new ArrayList<>();
        for(PostModel p : insertListModel) insertListEntity.add(p.castToPostEntity());

        Disposable insert = Completable.fromAction(() -> coreServices.getRoomService()
                .getDao()
                .insertPost(insertListEntity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        subscriptions.add(insert);
    }

    private void getPostsFromDb(){

        Disposable disposable = coreServices
                .getRoomService()
                .getDao()
                .getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    getViewState().log("DB size: " + result.size());
                    list.clear();
                    for(PostEntity p : result) list.add(p.castToPostModel());
                    getViewState().showPosts(list);
                    getViewState().hideProgressBar();
                }, throwable -> {
                    getViewState().log("Unsuccessful trying to get posts: " + throwable.getMessage());
                    getViewState().hideProgressBar();
                });

        subscriptions.add(disposable);
    }

    public List<PostModel> getList() {
        return list;
    }

}
