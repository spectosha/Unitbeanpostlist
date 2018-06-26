package postlist.unitbean.com.unitbeanpostlist.ui.main.presenters;

import android.content.ClipData;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.PostEntity;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.ImageNavigationViewModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.ItemNavigationViewModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.NavigationEnum;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.views.MainView;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.fragments.PostListFragment;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {


    List<NavigationEnum> list = new ArrayList<>();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        list.add(new ImageNavigationViewModel(R.drawable.splash_screen));
        list.add(new ItemNavigationViewModel(R.drawable.ic_post_list_24dp, "Posts"));
        list.add(new ItemNavigationViewModel(R.drawable.ic_settings_24dp, "Settings"));
        list.add(new ItemNavigationViewModel(R.drawable.ic_profile_24dp, "Profile"));
    }

    public void chooseNavigationViewItem(MenuItem item){

        switch(item.getItemId()){
            case R.id.to_post_list:
                getViewState().chooseFragmentLayout(new PostListFragment());
                break;
            case R.id.to_profile:
                getViewState().chooseFragmentLayout(new PostListFragment());
                break;
            case R.id.to_settings:
                getViewState().chooseFragmentLayout(new PostListFragment());
                break;
        }

        item.setChecked(true);
        getViewState().closeNavigationView();
    }

    public List<NavigationEnum> getList() {
        return list;
    }

}
