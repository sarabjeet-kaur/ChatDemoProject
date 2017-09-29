package com.example.chatdemoproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatdemoproject.R;
import com.example.chatdemoproject.bean.SendMessageModel;

import java.util.ArrayList;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class SendMessageListAdapter extends RecyclerView.Adapter<SendMessageListAdapter.CustomViewHolder> {
    private ArrayList<SendMessageModel> message = new ArrayList<>();
    private Context context;


    //CONSTRUCTOR
    public SendMessageListAdapter(ArrayList<SendMessageModel> message, Context context) {
        this.message = message;
        this.context = context;
    }


    @Override
    public SendMessageListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SendMessageListAdapter.CustomViewHolder holder, int position) {

        holder.text_message_body.setText(message.get(position).getTextMessage());
    }

    @Override
    public int getItemCount() {
        return (null != message ? message.size() : 0);

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView user_profile_image_url;
        TextView text_message_body;

        public CustomViewHolder(View view) {
            super(view);
            this.text_message_body = (TextView) view.findViewById(R.id.text_message_body);
        }
    }
}
