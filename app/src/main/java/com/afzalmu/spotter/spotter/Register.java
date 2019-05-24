package com.afzalmu.spotter.spotter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = (Button) findViewById(R.id.btn_register);
        Button Login = (Button) findViewById(R.id.btn_login);

        final EditText username_edit = (EditText) findViewById(R.id.username);
        final EditText password_edit = (EditText) findViewById(R.id.password);
        final EditText email_edit = (EditText) findViewById(R.id.email);
        final EditText mobile_edit = (EditText) findViewById(R.id.mobile);

//       database link path

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Register.this, "Login cliecked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username, email, password, phone;
                if (username_edit.getText().toString().length() == 0) {
                    username_edit.setError("Cannot be null");
                } else if (password_edit.getText().toString().length() == 0) {
                    password_edit.setError("Cannot be null");
                } else if (email_edit.getText().toString().length() == 0) {
                    email_edit.setError("Cannot be null");
                } else if (mobile_edit.getText().toString().length() == 0) {
                    mobile_edit.setError("Cannot be null");
                } else {
                    username = username_edit.getText().toString();
                    password = password_edit.getText().toString();
                    email = email_edit.getText().toString();
                    phone = mobile_edit.getText().toString();

                    username = username.toLowerCase();
                    username = username.trim();


//                Setting value of given variables in firebasedatabase through UserDetails Class
                   UserDetails userObject= new UserDetails(username, email, password, phone);
                    mDatabase.child(username).setValue(userObject)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Data saved to db", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Failed " + e, Toast.LENGTH_SHORT).show();


                        }
                    });


                }

            }
        });


    }
}
