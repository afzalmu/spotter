package com.afzalmu.spotter.spotter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    DatabaseReference mDatabase;


    ArrayList<String> usernameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.btn_login);
        Button register = (Button) findViewById(R.id.btn_register);

        final EditText username_edit = (EditText) findViewById(R.id.username);
        final EditText password_edit = (EditText) findViewById(R.id.password);

        usernameList=new ArrayList<>();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("users");


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot!=null){
                    usernameList.add(""+dataSnapshot.getKey());

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username, password;
                if (username_edit.getText().toString().length() == 0) {
                    username_edit.setError("Cannot be null");

                } else if (username_edit.getText().toString().length() == 0) {
                    username_edit.setError("Cannot be null");

                } else {
                    username = username_edit.getText().toString();
                    password = password_edit.getText().toString();

                    if(usernameList.contains(username)){
                            mDatabase.child(username).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot!=null){
                                        UserDetails userDetails=dataSnapshot.getValue(UserDetails.class);
                                        if(userDetails!=null){
                                            if(userDetails.getPassword().equals(password)){
                                                Toast.makeText(Login.this, "Signed in", Toast.LENGTH_SHORT).show();

                                                Intent i=new Intent(Login.this,ListOfUsers.class);
                                                startActivity(i);
                                                finish();
                                            }else{
                                                Toast.makeText(Login.this, "Wrong passsword", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                    }else{
                        Toast.makeText(Login.this, "User not found in db\nPlease register", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Login.this, "Register cliecked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
