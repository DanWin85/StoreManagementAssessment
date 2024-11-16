package com.example.storemanagementapp_2008043_danielwingate;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.storemanagementapp_2008043_danielwingate.DataModel.Customer;

import java.util.List;

public class AppViewModel extends ViewModel {
    AppDataModel dataModel = null;
    private Application application;

    public AppViewModel (Application application){
        dataModel = new AppDataModel(application);
    }

    public long insertCustomer(Customer customer) {
        return dataModel.insertCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        dataModel.updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        dataModel.deleteCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return dataModel.getAllCustomers();
    }

    public Customer getCustomerById(int customerId) {
        return dataModel.getCustomerById(customerId);
    }
}
