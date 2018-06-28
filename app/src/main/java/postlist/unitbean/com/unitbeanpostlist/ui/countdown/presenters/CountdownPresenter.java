package postlist.unitbean.com.unitbeanpostlist.ui.countdown.presenters;

import android.util.TimeFormatException;

import com.arellomobile.mvp.InjectViewState;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.countdown.views.CountdownView;

@InjectViewState
public class CountdownPresenter extends BasePresenter<CountdownView> {

    private boolean isTimer = false;

    private long startTime = 1 * 60 * 1000; // 1 минута

    private int STEP_MILLS = 100;

    private void startTimer(){

        Disposable disposable = Observable.interval(STEP_MILLS, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    result *= STEP_MILLS;
                    if(result > startTime) {
                        getViewState().showCurrentCountdown("0:00:000");
                        stopTimer();
                    }else{
                        long currentMills = startTime - result;
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(currentMills);
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentMills - TimeUnit.MINUTES.toMillis(minutes));
                        long milliseconds = currentMills - TimeUnit.MINUTES.toMillis(minutes) - TimeUnit.SECONDS.toMillis(seconds);

                        getViewState().showCurrentCountdown(minutes + ":" + seconds + ":" + milliseconds);
                    }
                });
        subscriptions.add(disposable);
    }

    public void setStartTime(int minutes, int seconds, int mills) throws IllegalArgumentException{
        if(minutes > 60 || minutes < 0) throw new IllegalArgumentException("Уou must specify a value within such limits: 0 <= minutes <= 60");
        if(seconds > 60 || seconds < 0) throw new IllegalArgumentException("You must specify a value within such limits: 0 <= seconds <= 60");
        if(mills > 1000 || mills < 0) throw new IllegalArgumentException("You must specify a value within such limits: 0 <= mills <= 1000");
        startTime = TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds) + mills;
    }

    public void stopTimer(){
        subscriptions.clear();
    }

    public void startOrStopTimer(){
        if(isTimer){
            stopTimer();
        }else{
            startTimer();
        }
        isTimer = !isTimer;
    }

}
