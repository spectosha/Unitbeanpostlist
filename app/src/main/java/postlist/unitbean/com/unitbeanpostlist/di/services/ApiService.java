package postlist.unitbean.com.unitbeanpostlist.di.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .build();

    private Api api;

    public Api getApi() {
        if(api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

    public interface Api{

        @GET("/posts")
        Observable<List<PostModel>> getPosts();

        @GET("/comments")
        Observable<List<CommentModel>> getComments(@Query("postId") int id);
    }
}
