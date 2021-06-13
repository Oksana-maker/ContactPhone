package com.example.contactphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactphone.adapters.ContactAdapter;
import com.example.contactphone.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactAdapterListener {

    static List<Contact> contacts = new ArrayList<>();

    static {
        contacts.add(new Contact("First name 1", "Last name 1", "0992431145", "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg"));
        contacts.add(new Contact("First name 2", "Last name 2", "0991431146", "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg"));
        contacts.add(new Contact("First name 3", "Last name 3", "0994431147", "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg"));
        contacts.add(new Contact("First name 4", "Last name 4", "0994431147", "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg"));
        contacts.add(new Contact("First name 5", "Last name 5", "0994431147", "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg"));

    }

    RecyclerView rvContacts;
    ContactAdapter contactAdapter;

    EditText edFirstName, edLastName, edPhone, edPhoto;

    Contact tmpContact;
    int tmpPosition;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edFirstName = findViewById(R.id.editFirstName);
        edLastName= findViewById(R.id.editLastName);
        edPhone = findViewById(R.id.editNumberPhone);

        btnSave = findViewById(R.id.btnSave);

        rvContacts = findViewById(R.id.rvContacts);

        contactAdapter = new ContactAdapter(this, contacts);
        rvContacts.setAdapter(contactAdapter);


        btnSave.setOnClickListener(v -> {
            if(tmpContact == null) tmpContact = new Contact("", "", "", "https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg");
            tmpContact.setFirstName(edFirstName.getText().toString());
            tmpContact.setLastName(edLastName.getText().toString());
            tmpContact.setPhone(edPhone.getText().toString());
            if(!contacts.contains(tmpContact)) {
                contacts.add(tmpContact);
                int position = contacts.indexOf(tmpContact);
                contactAdapter.notifyItemInserted(contacts.indexOf(tmpContact));
                contactAdapter.notifyItemRangeChanged(position, contactAdapter.getItemCount());
            }
            else{
                contactAdapter.notifyItemChanged(tmpPosition);
            }
            tmpContact = null;
            edFirstName.setText("First Name");
            edLastName.setText("Last Name");
            edPhone.setText("");
        });


    }

    @Override
    public void onRemoveClick(Contact contact, int position) {
        contacts.remove(contact);
      contactAdapter.notifyItemRemoved(position);
      contactAdapter.notifyItemRangeChanged(position,contactAdapter.getItemCount());
    }

    @Override
    public void onEditClick(Contact contact, int position) {

        edFirstName.setText(contact.getFirstName());
        edLastName.setText(contact.getLastName());
        edPhone.setText(contact.getPhone());

        tmpContact = contact;
        tmpPosition = position;
    }


}