package com.example.storemanagementapp_2008043_danielwingate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.storemanagementapp_2008043_danielwingate.DataModel.Customer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddCustomerActivity extends AppCompatActivity {

    private TextInputEditText etFirstName, etLastName, etBirthDate, etAddress, etSuburb, etCity, etZIP;
    private MaterialButton btnCancel, btnAddRecord;
    AppViewModel viewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_customer);
        initializeViews();
        setupClickListeners();

    }

    private void initializeViews(){
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etAddress = findViewById(R.id.etAddress);
        etSuburb = findViewById(R.id.etSuburb);
        etCity = findViewById(R.id.etCity);
        etZIP = findViewById(R.id.etZip);
        btnCancel = findViewById(R.id.btnCancel);
        btnAddRecord = findViewById(R.id.btnAddRecord);

        viewModel = new AppViewModel(getApplication());
    }

    private void setupClickListeners() {
        btnCancel.setOnClickListener(v -> finish());

        btnAddRecord.setOnClickListener(v -> {
            if (validateInputs()) {
                addCustomer();
            }
        });
    }

    private boolean validateInputs() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String birthDate = etBirthDate.getText().toString().trim();

        if (firstName.isEmpty()) {
            etFirstName.setError("First name is required");
            return false;
        }

        if (lastName.isEmpty()) {
            etLastName.setError("Last name is required");
            return false;
        }

        if (birthDate.isEmpty()) {
            etBirthDate.setError("Date of birth is required");
            return false;
        }

        // Validate date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(birthDate);
        } catch (ParseException e) {
            etBirthDate.setError("Invalid date format. Use dd/mm/yyyy");
            return false;
        }

        return true;
    }

    private void addCustomer() {
        new Thread(() -> {
            try {
                // Get all input values
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String suburb = etSuburb.getText().toString().trim();
                String city = etCity.getText().toString().trim();
                String zip = etZIP.getText().toString().trim();

                // Parse birth date
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date birthDate = dateFormat.parse(etBirthDate.getText().toString().trim());

                // Create customer object with current date as registration date
                Customer customer = new Customer(
                        0, // ID will be auto-generated
                        firstName,
                        lastName,
                        birthDate,
                        address,
                        suburb,
                        city,
                        zip,
                        new Date() // Current date for registration
                );

                // Insert customer using ViewModel
                long result = viewModel.insertCustomer(customer);

                runOnUiThread(() -> {
                    if (result > 0) {
                        Toast.makeText(this, "Customer added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, ManageCustomersActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to add customer", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (ParseException e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Error parsing date", Toast.LENGTH_SHORT).show()
                );
            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Error adding customer: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }
}