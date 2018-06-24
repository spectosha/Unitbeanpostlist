package postlist.unitbean.com.unitbeanpostlist.ui.splash.activities;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.main.activities.MainActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.splash.presenters.SplashScreenPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.splash.views.SplashScreenView;

public class SplashScreenActivity extends BaseActivity implements SplashScreenView {

    @InjectPresenter
    SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.handleNextScreen();
    }

    @Override
    public void onNextScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
