package com.example.storemanagementapp_2008043_danielwingate;

import android.app.Application;

import com.example.storemanagementapp_2008043_danielwingate.DataModel.Customer;
import com.example.storemanagementapp_2008043_danielwingate.DataModel.StoreDao;
import com.example.storemanagementapp_2008043_danielwingate.DataModel.StoreDatabase;

import java.util.List;

public class AppDataModel {
    private StoreDao storeDao;

    public AppDataModel(Application application){
        StoreDatabase database = StoreDatabase.createDatabaseInstance(application);
        storeDao = database.getStoreDao();
    }

    public long insertCustomer(final Customer customer) {
        try {
            // Use ExecutorService to run on background thread
            return java.util.concurrent.Executors.newSingleThreadExecutor().submit(() ->
                    storeDao.insertCustomer(customer)
            ).get(); // .get() waits for the result
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void updateCustomer(Customer customer) {
        storeDao.updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        storeDao.deleteCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return storeDao.getAllCustomers();
    }

    public Customer getCustomerById(int customerId) {
        return storeDao.getCustomerById(customerId);
    }
}
