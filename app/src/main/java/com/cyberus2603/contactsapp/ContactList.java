package com.cyberus2603.contactsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class ContactList extends RecyclerView.Adapter<ContactList.ContactListRow> {

    Context context;
    int avatars[];
    Vector<String> names,phone_numbers;
    Vector<MainActivity.Date_of_Birth> dates_of_birth;

    public ContactList(Context context, int avatars[] , Vector<String> names,
                       Vector<MainActivity.Date_of_Birth> dates_of_birth, Vector<String> phone_numbers) {
        this.context = context;
        this.avatars = avatars;
        this.names = names;
        this.dates_of_birth = dates_of_birth;
        this.phone_numbers = phone_numbers;
    }

    @NonNull
    @Override
    public ContactListRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_list_row, parent, false);
        return new ContactListRow(view);
    }

    public void updateData() {
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ContactListRow holder, final int position) {
        holder.name_and_surname.setText(names.get(position));
        holder.avatar.setImageResource(avatars[position%16]);
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.remove(position);
                phone_numbers.remove(position);
                dates_of_birth.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.mainLayoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailedShow.class);
                String dob = dates_of_birth.get(position).getAll();
                intent.putExtra("name_and_surname", names.get(position));
                intent.putExtra("phone_number", phone_numbers.get(position));
                intent.putExtra("avatar",avatars[position]);
                intent.putExtra("date_of_birth",dob);
                context.startActivity(intent);
            }
        });
        holder.mainLayoutRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder.setMessage("Are you sure ?");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton(
                        "Call",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(context, "Called",Toast.LENGTH_SHORT).show();
                            }
                        });
                dialogBuilder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog callDialog = dialogBuilder.create();
                callDialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ContactListRow extends RecyclerView.ViewHolder {

        TextView name_and_surname;
        ImageView avatar;
        ImageButton delete_button;
        ConstraintLayout mainLayoutRow;

        public ContactListRow(@NonNull View itemView) {
            super(itemView);
            name_and_surname = itemView.findViewById(R.id.nameAndSurname);
            avatar = itemView.findViewById(R.id.contactAvatar);
            delete_button = itemView.findViewById(R.id.deleteButton);
            mainLayoutRow = itemView.findViewById(R.id.mainLayoutRow);
        }
    }
}

