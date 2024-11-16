package com.example.storemanagementapp_2008043_danielwingate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.storemanagementapp_2008043_danielwingate.DataModel.Customer;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private List<Customer> customers;
    private OnCustomerClickListener listener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public interface OnCustomerClickListener {
        void onCustomerClick(Customer customer);
    }

    public CustomerAdapter(List<Customer> customers, OnCustomerClickListener listener) {
        this.customers = customers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_list_item, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customers.get(position);
        holder.tvCustomerId.setText("ID: " + customer.getCustomerID());
        holder.tvCustomerName.setText(customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
        holder.tvRegistrationDate.setText("Registered: " + dateFormat.format(customer.getCustomerRegistrationDate()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCustomerClick(customer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers != null ? customers.size() : 0;
    }

    public void updateCustomers(List<Customer> newCustomers) {
        this.customers = newCustomers;
        notifyDataSetChanged();
    }

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView tvCustomerId, tvCustomerName, tvRegistrationDate;

        CustomerViewHolder(View itemView) {
            super(itemView);
            tvCustomerId = itemView.findViewById(R.id.tvCustomerId);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvRegistrationDate = itemView.findViewById(R.id.tvRegistrationDate);
        }
    }
}