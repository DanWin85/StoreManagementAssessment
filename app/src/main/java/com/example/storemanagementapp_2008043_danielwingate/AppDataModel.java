package com.example.storemanagementapp_2008043_danielwingate;

import android.app.Application;

import com.example.storemanagementapp_2008043_danielwingate.DataModel.StoreDao;
import com.example.storemanagementapp_2008043_danielwingate.DataModel.StoreDatabase;

public class AppDataModel {
    private StoreDao storeDao;

    public AppDataModel(Application application){
        StoreDatabase database = StoreDatabase.createDatabaseInstance(application);
        storeDao = database.getStoreDao();
    }
}
