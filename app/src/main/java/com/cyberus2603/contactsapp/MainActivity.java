package com.cyberus2603.contactsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Context context;
    RecyclerView ContactListRW;
    FloatingActionButton AddContact;
    ContactList contactList;
    ImageView avatarDetailed;
    TextView nameAndSurnameDetailed, dateOfBirthDetailed, phoneNumberDetailed;

    public class Date_of_Birth {
        private int day;
        private int month;
        private int year;

        public Date_of_Birth(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public String getAll(){
            return (day + "-" + month + "-" + year);
        }
    }

    int avatars[] = {R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3, R.drawable.avatar_4,
    R.drawable.avatar_5, R.drawable.avatar_6, R.drawable.avatar_7, R.drawable.avatar_8,
    R.drawable.avatar_9, R.drawable.avatar_10, R.drawable.avatar_11, R.drawable.avatar_12,
    R.drawable.avatar_13, R.drawable.avatar_14, R.drawable.avatar_15, R.drawable.avatar_16};
    Vector<String> names;
    Vector<Date_of_Birth> dates_of_birth;
    Vector<String> phone_numbers;

    public void addData(String name, Date_of_Birth dof, String number) {
        names.add(name);
        dates_of_birth.add(dof);
        phone_numbers.add(number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = this;

        AddContact = findViewById(R.id.AddContactButton);

        names = new Vector<String>(1);
        dates_of_birth = new Vector<Date_of_Birth>(1);
        phone_numbers = new Vector<String>(1);

        Date_of_Birth test = new Date_of_Birth(1,1,2000);
        Date_of_Birth example = new Date_of_Birth(10,10,2010);

        names.add("Test Testowy");
        dates_of_birth.add(test);
        phone_numbers.add("999999999");
        names.add("Example Examplowy");
        dates_of_birth.add(example);
        phone_numbers.add("111111111");

        AddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddContactActivity.class);
                startActivityForResult(intent,1);
            }
        });

        ContactListRW = findViewById(R.id.ContactListRecycleView);

        contactList = new ContactList(this,avatars,names,dates_of_birth,phone_numbers);
        ContactListRW.setAdapter(contactList);
        ContactListRW.setLayoutManager(new LinearLayoutManager(this));

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            avatarDetailed = findViewById(R.id.avatarLand);
            nameAndSurnameDetailed = findViewById(R.id.nameAndSurnameLand);
            dateOfBirthDetailed = findViewById(R.id.dateOfBirthLand);
            phoneNumberDetailed = findViewById(R.id.phoneNumberLand);
            avatarDetailed.setImageResource(avatars[0]);
            nameAndSurnameDetailed.setText(names.get(0));
            Date_of_Birth date = dates_of_birth.get(0);
            dateOfBirthDetailed.setText("Date of Birth: " + date.getAll());
            phoneNumberDetailed.setText("Phone number: " + phone_numbers.get(0));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactList.updateData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String name,number;
            int day,month,year;
            Intent received_intent = data;
            name = received_intent.getStringExtra("NAME");
            day = received_intent.getIntExtra("DAY",1);
            month = received_intent.getIntExtra("MONTH",1);
            year = received_intent.getIntExtra("YEAR",1);
            number = received_intent.getStringExtra("NUMBER");
            Date_of_Birth tmp = new Date_of_Birth(day,month,year);
            addData(name,tmp,number);
        }
    }
}
