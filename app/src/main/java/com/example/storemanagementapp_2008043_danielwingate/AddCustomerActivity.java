package com.example.storemanagementapp_2008043_danielwingate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etBirthDate, etAddress, etSuburb, etCity, etZIP;
    private MaterialButton btnCancel, btnAddRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_customer);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etAddress = findViewById(R.id.etAddress);
        etSuburb = findViewById(R.id.etSuburb);
        etCity = findViewById(R.id.etCity);
        etZIP = findViewById(R.id.etZip);
        btnCancel = findViewById(R.id.btnCancel);
        btnAddRecord = findViewById(R.id.btnAddRecord);

    }
}