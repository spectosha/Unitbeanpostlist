package postlist.unitbean.com.unitbeanpostlist.ui.base.activities;

import android.support.v7.app.AlertDialog;

import com.arellomobile.mvp.MvpAppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;

public class BaseActivity extends MvpAppCompatActivity implements BaseView{

    private AlertDialog errorDialog;

    public void onError(String msg) {
        if (errorDialog == null || !errorDialog.isShowing()) {
            errorDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog)
                    .setTitle(R.string.common_error)
                    .setMessage(msg)
                    .setPositiveButton(R.string.common_ok, null)
                    .create();
        }
    }
}
