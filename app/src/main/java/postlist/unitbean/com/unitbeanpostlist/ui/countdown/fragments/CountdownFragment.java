package postlist.unitbean.com.unitbeanpostlist.ui.countdown.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.countdown.presenters.CountdownPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.countdown.views.CountdownView;

public class CountdownFragment extends BaseFragment implements CountdownView {

    @InjectPresenter
    CountdownPresenter presenter;

    private TextView display;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_countdown, container, false);

        display = v.findViewById(R.id.countdown_display);
        button = v.findViewById(R.id.countdown_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startOrStopTimer();
            }
        });
        // Указываем начальное время
        presenter.setStartTime(0, 10, 0);

        return v;
    }


    @Override
    public void showCurrentCountdown(String currentTime) {
        display.setText(currentTime);
    }
}
