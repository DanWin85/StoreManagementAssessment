package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {Customer.class, CustomerOrder.class, OrderDetail.class, Product.class},
        version = 1,
        exportSchema = false
)
@TypeConverters(DataTypeConverter.class)
public abstract class StoreDatabase extends RoomDatabase {

    private static StoreDatabase instance = null;
    public abstract StoreDao getStoreDao();

    public static StoreDatabase createDatabaseInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    StoreDatabase.class,
                    "Store_Database"
            )
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
