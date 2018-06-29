package postlist.unitbean.com.unitbeanpostlist.ui.main.presenters;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.ImageNavigationViewModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.ItemNavigationViewModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.NavigationEnum;
import postlist.unitbean.com.unitbeanpostlist.ui.main.views.MainView;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    List<NavigationEnum> list = new ArrayList<>();


    List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        list.add(new ImageNavigationViewModel(R.drawable.splash_screen));
        list.add(new ItemNavigationViewModel(R.drawable.ic_post_list_24dp, "Posts"));
        list.add(new ItemNavigationViewModel(R.drawable.ic_profile_24dp, "Timer"));
    }

    public List<NavigationEnum> getList() {
        return list;
    }

    public List<BaseFragment> getFragments() {
        return fragments;
    }

    public void addFragment(BaseFragment f) {
        fragments.add(f);
    }

}
