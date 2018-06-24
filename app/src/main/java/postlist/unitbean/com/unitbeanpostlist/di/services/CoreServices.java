package postlist.unitbean.com.unitbeanpostlist.di.services;

import javax.inject.Inject;

import postlist.unitbean.com.unitbeanpostlist.di.services.db.UbDatabase;

public class CoreServices {
    @Inject ApiService apiService;
    @Inject RoomService roomService;

    public ApiService getApiService() {
        return apiService;
    }

    public RoomService getRoomService() {
        return roomService;
    }
}
