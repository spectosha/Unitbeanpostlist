package postlist.unitbean.com.unitbeanpostlist.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import postlist.unitbean.com.unitbeanpostlist.di.services.ApiService;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public ApiService provideApiService() {
        return new ApiService();
    }

}
