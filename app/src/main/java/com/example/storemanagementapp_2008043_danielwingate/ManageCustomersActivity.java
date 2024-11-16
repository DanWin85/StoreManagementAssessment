package com.example.storemanagementapp_2008043_danielwingate;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.storemanagementapp_2008043_danielwingate.DataModel.Customer;
import java.util.ArrayList;
import java.util.List;

public class ManageCustomersActivity extends AppCompatActivity implements CustomerAdapter.OnCustomerClickListener {

    private RecyclerView rvCustomers;
    private CustomerAdapter adapter;
    private AppViewModel viewModel;
    private List<Customer> customerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_customer);

        viewModel = new AppViewModel(getApplication());

        initializeViews();
        loadCustomers();
    }

    private void initializeViews() {
        rvCustomers = findViewById(R.id.rvCustomers);
        rvCustomers.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomerAdapter(customerList, this);
        rvCustomers.setAdapter(adapter);
    }

    private void loadCustomers() {
        new Thread(() -> {
            List<Customer> customers = viewModel.getAllCustomers();
            runOnUiThread(() -> {
                customerList.clear();
                customerList.addAll(customers);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }

    @Override
    public void onCustomerClick(Customer customer) {
        Intent intent = new Intent(this, CustomerDetailsActivity.class);
        intent.putExtra("customer_id", customer.getCustomerID());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCustomers();
    }
}