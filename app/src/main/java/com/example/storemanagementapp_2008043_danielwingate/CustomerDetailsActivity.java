package com.example.storemanagementapp_2008043_danielwingate;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storemanagementapp_2008043_danielwingate.DataModel.Customer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomerDetailsActivity extends AppCompatActivity {

    private TextInputEditText etCustomerId, etRegistrationDate, etFirstName, etLastName,
            etBirthDate, etAddress, etSuburb, etCity, etZIP;
    private MaterialButton btnEdit, btnUpdate, btnDelete, btnCancel;
    private AppViewModel viewModel;
    private Customer currentCustomer;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        initializeViews();
        setupClickListeners();
        loadCustomerData();
    }

    private void initializeViews() {
        etCustomerId = findViewById(R.id.etCustomerId);
        etRegistrationDate = findViewById(R.id.etRegistrationDate);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etAddress = findViewById(R.id.etAddress);
        etSuburb = findViewById(R.id.etSuburb);
        etCity = findViewById(R.id.etCity);
        etZIP = findViewById(R.id.etZip);

        btnEdit = findViewById(R.id.btnEdit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        viewModel = new AppViewModel(getApplication());
    }

    private void setupClickListeners() {
        btnEdit.setOnClickListener(v -> enableEditing(true));
        btnUpdate.setOnClickListener(v -> updateCustomer());
        btnDelete.setOnClickListener(v -> confirmDelete());
        btnCancel.setOnClickListener(v -> {
            if (btnUpdate.isEnabled()) {
                enableEditing(false);
                displayCustomerData();
            } else {
                finish();
            }
        });
    }

    private void loadCustomerData() {
        int customerId = getIntent().getIntExtra("customer_id", -1);
        if (customerId == -1) {
            Toast.makeText(this, "Error loading customer", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        new Thread(() -> {
            currentCustomer = viewModel.getCustomerById(customerId);
            runOnUiThread(() -> {
                if (currentCustomer != null) {
                    displayCustomerData();
                } else {
                    Toast.makeText(this, "Customer not found", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }).start();
    }

    private void displayCustomerData() {
        etCustomerId.setText(String.valueOf(currentCustomer.getCustomerID()));
        etRegistrationDate.setText(dateFormat.format(currentCustomer.getCustomerRegistrationDate()));
        etFirstName.setText(currentCustomer.getCustomerFirstName());
        etLastName.setText(currentCustomer.getCustomerLastName());
        etBirthDate.setText(dateFormat.format(currentCustomer.getCustomerDateOfBirth()));
        etAddress.setText(currentCustomer.getCustomerAddress());
        etSuburb.setText(currentCustomer.getCustomerSuburb());
        etCity.setText(currentCustomer.getCustomerCity());
        etZIP.setText(currentCustomer.getCustomerZIP());
    }

    private void enableEditing(boolean enable) {
        etFirstName.setEnabled(enable);
        etLastName.setEnabled(enable);
        etBirthDate.setEnabled(enable);
        etAddress.setEnabled(enable);
        etSuburb.setEnabled(enable);
        etCity.setEnabled(enable);
        etZIP.setEnabled(enable);

        btnUpdate.setEnabled(enable);
        btnEdit.setEnabled(!enable);
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
        try {
            dateFormat.parse(birthDate);
        } catch (ParseException e) {
            etBirthDate.setError("Invalid date format. Use dd/mm/yyyy");
            return false;
        }

        return true;
    }

    private void updateCustomer() {
        if (!validateInputs()) {
            return;
        }

        try {
            // Update customer object with new values
            currentCustomer.setCustomerFirstName(etFirstName.getText().toString().trim());
            currentCustomer.setCustomerLastName(etLastName.getText().toString().trim());
            currentCustomer.setCustomerDateOfBirth(dateFormat.parse(etBirthDate.getText().toString().trim()));
            currentCustomer.setCustomerAddress(etAddress.getText().toString().trim());
            currentCustomer.setCustomerSuburb(etSuburb.getText().toString().trim());
            currentCustomer.setCustomerCity(etCity.getText().toString().trim());
            currentCustomer.setCustomerZIP(etZIP.getText().toString().trim());

            // Update in database
            new Thread(() -> {
                viewModel.updateCustomer(currentCustomer);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Customer updated successfully", Toast.LENGTH_SHORT).show();
                    enableEditing(false);
                });
            }).start();

        } catch (ParseException e) {
            Toast.makeText(this, "Error parsing date", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error updating customer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Customer")
                .setMessage("Are you sure you want to delete this customer?")
                .setPositiveButton("Delete", (dialog, which) -> deleteCustomer())
                .setNegativeButton("Cancel", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteCustomer() {
        new Thread(() -> {
            try {
                viewModel.deleteCustomer(currentCustomer);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Customer deleted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error deleting customer: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}

