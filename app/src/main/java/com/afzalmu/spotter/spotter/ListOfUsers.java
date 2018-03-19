package com.afzalmu.spotter.spotter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOfUsers extends AppCompatActivity {
    DatabaseReference mDatabase;
    ArrayList<String> userList=new ArrayList<>();
    ListView list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("users");

        list=(ListView)findViewById(R.id.list);
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, userList);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot!=null){
                    UserDetails userDetails=dataSnapshot.getValue(UserDetails.class);
                    if(userDetails!=null){
                        userList.add(userDetails.getUsername());

                        list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) list.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(), itemValue , Toast.LENGTH_LONG)
                        .show();
                Intent i =new Intent(ListOfUsers.this,Maps.class);
                startActivity(i);

            }

        });

    }
}
