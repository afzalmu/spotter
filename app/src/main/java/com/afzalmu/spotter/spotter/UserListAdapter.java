package com.afzalmu.spotter.spotter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserListAdapter  extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    Context context;
    ArrayList<UserDetails> itemList;

    public UserListAdapter(Context context, ArrayList<UserDetails> itemList) {
        this.context = context;
        this.itemList = itemList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_item_layout,parent,false);
        UserListAdapter.ViewHolder viewHolder=new UserListAdapter.ViewHolder(view);
        return viewHolder;
    }


//    requesting and sending location via sms message
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         final UserDetails userDetails=itemList.get(position);
        holder.name.setText(userDetails.getUsername());




        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(userDetails.getMobile(),null,"Hi, I am requesting your location. Please share it with me\n\nhttp://spotter.pk/share",null,null);
                Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show();
            }
        });

        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(userDetails.getMobile(),null,"Hi this is my location\n\nPlease click on link to view on map\n\nhttp://spotter.pk/location/"+SharedPrefs.getUsername()+","+SharedPrefs.getLat()+","+SharedPrefs.getLon(),null,null);
                Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button send,request;
        public ViewHolder(View itemView) {
            super(itemView);
            request=itemView.findViewById(R.id.request);
            name=itemView.findViewById(R.id.name);
            send=itemView.findViewById(R.id.send);
        }
    }


}
