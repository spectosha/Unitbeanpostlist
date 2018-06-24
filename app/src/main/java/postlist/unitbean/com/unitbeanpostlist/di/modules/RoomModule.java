package postlist.unitbean.com.unitbeanpostlist.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import postlist.unitbean.com.unitbeanpostlist.di.services.RoomService;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.UbDatabase;

@Module
public class RoomModule {

    @Provides
    @Singleton
    RoomService provideDatabase(Context context){
        return new RoomService(context);
    }

}
