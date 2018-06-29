package postlist.unitbean.com.unitbeanpostlist.ui.timer.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;


import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.timer.presenters.TimerPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.timer.views.TimerView;

public class TimerFragment extends BaseFragment implements TimerView {

    @InjectPresenter
    TimerPresenter presenter;

    private Button buttonStart;
    private TextView timerDisplay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timer, container, false);

        timerDisplay = v.findViewById(R.id.timer_display);
        buttonStart = v.findViewById(R.id.timer_button);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.initialiseTimer(1,0,0);
            }
        });

        return v;
    }

    @Override
    public void updateTimer(String time){
        timerDisplay.setText(time);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
