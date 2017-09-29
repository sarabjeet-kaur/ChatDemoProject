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
import com.example.chatdemoproject.fragment.ChatFragment;

import java.util.ArrayList;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class SendMessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SendMessageModel> message = new ArrayList<>();
    private Context context;
    String type;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;


    //CONSTRUCTOR
    public SendMessageListAdapter(ArrayList<SendMessageModel> message, Context context) {
        this.message = message;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        SendMessageModel type=(SendMessageModel)message.get(position);

        if (type.getMessageType().equalsIgnoreCase(ChatFragment.TYPE_SEND)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_recieve, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder.getItemViewType() == VIEW_TYPE_MESSAGE_RECEIVED){
            ReceivedMessageHolder myholder = (ReceivedMessageHolder) holder;
            myholder.text_message_body.setText(message.get(position).getTextMessage());

        }else {
            SentMessageHolder myholder = (SentMessageHolder) holder;
            myholder.text_message_body.setText(message.get(position).getTextMessage());
        }

    }

    @Override
    public int getItemCount() {
        return (null != message ? message.size() : 0);

    }

    class SentMessageHolder extends RecyclerView.ViewHolder {
        ImageView user_profile_image_url;
        TextView text_message_body;

        public SentMessageHolder(View view) {
            super(view);
            this.text_message_body = (TextView) view.findViewById(R.id.text_message_body);
        }
    }
    class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        ImageView user_profile_image_url;
        TextView text_message_body;

        public ReceivedMessageHolder(View view) {
            super(view);
            this.text_message_body = (TextView) view.findViewById(R.id.text_message_body);
        }
    }
}
