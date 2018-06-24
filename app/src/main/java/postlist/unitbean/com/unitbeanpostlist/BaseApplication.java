package postlist.unitbean.com.unitbeanpostlist;

import android.app.Application;

import postlist.unitbean.com.unitbeanpostlist.di.components.AppComponent;
import postlist.unitbean.com.unitbeanpostlist.di.components.DaggerAppComponent;
import postlist.unitbean.com.unitbeanpostlist.di.modules.ApiModule;
import postlist.unitbean.com.unitbeanpostlist.di.modules.ContextModule;
import postlist.unitbean.com.unitbeanpostlist.di.modules.RoomModule;

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .contextModule(new ContextModule(getApplicationContext()))
                .roomModule(new RoomModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
