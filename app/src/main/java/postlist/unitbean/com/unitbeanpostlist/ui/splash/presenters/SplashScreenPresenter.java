package postlist.unitbean.com.unitbeanpostlist.ui.splash.presenters;

import com.arellomobile.mvp.InjectViewState;

import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.splash.views.SplashScreenView;

@InjectViewState
public class SplashScreenPresenter extends BasePresenter<SplashScreenView> {

    public void handleNextScreen() {
       getViewState().onNextScreen();
    }

}
