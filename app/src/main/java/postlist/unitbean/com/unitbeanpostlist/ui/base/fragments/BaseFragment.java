package postlist.unitbean.com.unitbeanpostlist.ui.base.fragments;

import android.content.Context;

import com.arellomobile.mvp.MvpAppCompatFragment;

import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;

public class BaseFragment extends MvpAppCompatFragment implements BaseView{

    protected BaseActivity activity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            activity = (BaseActivity) context;
        }
    }

    public void onError(String msg){
        activity.onError(msg);
    }

}
