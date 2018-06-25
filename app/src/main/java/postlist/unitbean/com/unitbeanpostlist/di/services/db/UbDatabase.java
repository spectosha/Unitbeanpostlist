package postlist.unitbean.com.unitbeanpostlist.di.services.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import dagger.Provides;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.MainDao;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.PostEntity;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;

@Database(entities = {PostEntity.class}, version = UbDatabase.DATABASE_VERSION, exportSchema = false)
public abstract class UbDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;

    private static UbDatabase INSTANCE;

    public static UbDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UbDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UbDatabase.class, "unitbean_db")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    public abstract MainDao getRoomDao();
}
