package com.cyberus2603.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailedShow extends AppCompatActivity {

    ImageView avatar;
    TextView name_and_surname, date_of_birth,phone_number;

    String name,date,number;
    int av_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_show);

        avatar = findViewById(R.id.contactAvatarDetailed);
        name_and_surname = findViewById(R.id.nameAndSurnameDetailed);
        date_of_birth = findViewById(R.id.contactBirthDateDetailed);
        phone_number = findViewById(R.id.contactPhoneNumberDetailed);

        getData();
        setData();

    }

    private void getData() {
        if(getIntent().hasExtra("name_and_surname") && getIntent().hasExtra("phone_number")
        && getIntent().hasExtra("avatar") && getIntent().hasExtra("date_of_birth")) {
            name = getIntent().getStringExtra("name_and_surname");
            date = getIntent().getStringExtra("date_of_birth");
            number = getIntent().getStringExtra("phone_number");
            av_img = getIntent().getIntExtra("avatar",1);
        } else {
            Toast.makeText(this, "No data",Toast.LENGTH_SHORT).show();
        }

    }

    private void setData() {
        avatar.setImageResource(av_img);
        name_and_surname.setText(name);
        date_of_birth.setText("Date of Birth: " + date);
        phone_number.setText("Phone number: " + number);
    }
}
