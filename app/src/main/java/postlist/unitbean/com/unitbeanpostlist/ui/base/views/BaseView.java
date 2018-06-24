package postlist.unitbean.com.unitbeanpostlist.ui.base.views;

import android.util.Log;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {

    static String LOG_TAG = "UNITBEAN";

    void onError(String msg);

    default void log(String msg){
            Log.i(LOG_TAG,msg);
    }
}
