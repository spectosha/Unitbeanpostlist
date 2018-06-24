package postlist.unitbean.com.unitbeanpostlist.ui.base.presenters;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import postlist.unitbean.com.unitbeanpostlist.BaseApplication;
import postlist.unitbean.com.unitbeanpostlist.di.services.CoreServices;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    protected CoreServices coreServices = new CoreServices();

    public CompositeDisposable subscriptions = new CompositeDisposable();

    public BasePresenter() {
        BaseApplication.getAppComponent().inject(coreServices);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscriptions.clear();
    }

}
