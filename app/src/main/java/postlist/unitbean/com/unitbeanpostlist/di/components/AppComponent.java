package postlist.unitbean.com.unitbeanpostlist.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import postlist.unitbean.com.unitbeanpostlist.di.modules.ApiModule;
import postlist.unitbean.com.unitbeanpostlist.di.modules.ContextModule;
import postlist.unitbean.com.unitbeanpostlist.di.modules.RoomModule;
import postlist.unitbean.com.unitbeanpostlist.di.services.CoreServices;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.UbDatabase;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, RoomModule.class})
public interface AppComponent {
    Context getContext();
    void inject(CoreServices coreServices);
}
