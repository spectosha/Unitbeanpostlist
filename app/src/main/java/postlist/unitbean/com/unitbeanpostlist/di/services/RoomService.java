package postlist.unitbean.com.unitbeanpostlist.di.services;

import android.content.Context;

import postlist.unitbean.com.unitbeanpostlist.di.services.db.UbDatabase;
import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.MainDao;

public class RoomService {

    private Context context;

    public RoomService(Context context){
        this.context = context;
    }

    public MainDao getDao(){
        return UbDatabase.getDatabase(context).getRoomDao();
    }
}
