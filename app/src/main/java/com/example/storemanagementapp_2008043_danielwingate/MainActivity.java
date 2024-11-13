package com.example.storemanagementapp_2008043_danielwingate;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnAddCustomer, btnManageCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();
        setupListeners();

    }

    private void initViews(){
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnManageCustomer = findViewById(R.id.btnManageCustomer);
    }

    private void setupListeners(){
        btnAddCustomer.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, AddCustomerActivity.class));
        });
        btnManageCustomer.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ManageCustomersActivity.class));
        });
    }
}