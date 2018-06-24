package postlist.unitbean.com.unitbeanpostlist.di.services.db.entities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(PostEntity post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(List<PostEntity> posts);

    @Query("SELECT * FROM " + PostEntity.TABLENAME)
    Flowable<List<PostEntity>> getAllPosts();

    @Query("DELETE FROM " + PostEntity.TABLENAME)
    void clearPostsTable();
}
