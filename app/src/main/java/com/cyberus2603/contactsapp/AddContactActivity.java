package com.cyberus2603.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    Context context;
    Button AddButton;
    EditText NameAndSurnameInsertField;
    EditText DateOfBirthInsertField ;
    EditText PhoneNumberInsertField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        context = this;
        AddButton = findViewById(R.id.addContactButtonAddScreen);
        NameAndSurnameInsertField = findViewById(R.id.nameInsertField);
        DateOfBirthInsertField = findViewById(R.id.dateInsertField);
        PhoneNumberInsertField = findViewById(R.id.numberInsertField);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_and_surname_tmp, date_tmp, phone_tmp;
                name_and_surname_tmp = NameAndSurnameInsertField.getText().toString();
                date_tmp = DateOfBirthInsertField.getText().toString();
                phone_tmp = PhoneNumberInsertField.getText().toString();
                String date_parts[] = date_tmp.split("-");
                if (phone_tmp.length() != 9) {
                    Toast.makeText(context, "Incorrect phone number",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (date_parts.length != 3) {
                    Toast.makeText(context, "Incorrect date of birth",Toast.LENGTH_SHORT).show();
                    return;
                }
                int date_day,date_month,date_year;
                date_day = Integer.parseInt(date_parts[0]);
                date_month = Integer.parseInt(date_parts[1]);
                date_year = Integer.parseInt(date_parts[2]);

                if (date_day < 1 || date_day > 31) {
                    Toast.makeText(context, "Incorrect day",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (date_month < 1 || date_month > 12) {
                    Toast.makeText(context, "Incorrect month",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(),AddContactActivity.class);
                intent.putExtra("NAME",name_and_surname_tmp);
                intent.putExtra("DAY",date_day);
                intent.putExtra("MONTH",date_month);
                intent.putExtra("YEAR",date_year);
                intent.putExtra("NUMBER",phone_tmp);
                setResult(1,intent);
                finish();;
            }
        });

    }
}
