package com.example.contactphone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactphone.R;
import com.example.contactphone.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public interface OnContactAdapterListener{
        void onRemoveClick(Contact contact, int position);
        void onEditClick(Contact contact, int position);

    }

    private final LayoutInflater inflater;
    private final List<Contact> contacts;
    private final Context context;
    private OnContactAdapterListener onContactAdapterListener;

    public ContactAdapter(Context context, List<Contact> contacts)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.contacts = contacts;

        if (context instanceof OnContactAdapterListener) {
            onContactAdapterListener = (OnContactAdapterListener) context;
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contact_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);


        Picasso.get().load(contact.getImagePath()).into(holder.imageView);
        holder.tvFirstName.setText(contact.getFirstName());
        holder.tvLastName.setText(contact.getLastName());
        holder.tvPhone.setText(contact.getPhone());

        holder.btnEdit.setOnClickListener(v -> {
            onContactAdapterListener.onEditClick(contact, position);
        });

        holder.btnRemove.setOnClickListener(v -> {
            onContactAdapterListener.onRemoveClick(contact, position);
        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstName, tvLastName, tvPhone;

        ImageView imageView;

        Button btnEdit, btnRemove;

        public ViewHolder(@NonNull View view)
        {
            super(view);

            tvFirstName = view.findViewById(R.id.tvFirstName);
            tvLastName= view.findViewById(R.id.tvLastName);
            tvPhone = view.findViewById(R.id.tvPhone);
            imageView = view.findViewById(R.id.imgViewUserPhoto);

            btnEdit = view.findViewById(R.id.btnEditContact);
            btnRemove = view.findViewById(R.id.btnRemoveContact);

        }
    }
}
