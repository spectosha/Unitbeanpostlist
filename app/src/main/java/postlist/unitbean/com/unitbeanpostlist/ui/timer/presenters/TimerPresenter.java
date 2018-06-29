package postlist.unitbean.com.unitbeanpostlist.ui.timer.presenters;

import android.os.health.TimerStat;

import com.arellomobile.mvp.InjectViewState;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.timer.views.TimerView;

@InjectViewState
public class TimerPresenter extends BasePresenter<TimerView> {

    private int minutes;
    private int seconds;

    public void initialiseTimer(int m, int s, int ms) {
        minutes = m;
        seconds = s;

        Disposable disposable = Observable.interval(-1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> {




                    getViewState().updateTimer(String.valueOf(time));
                });

        subscriptions.add(disposable);
    }
}
