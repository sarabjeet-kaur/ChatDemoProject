package com.example.chatdemoproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chatdemoproject.R;
import com.example.chatdemoproject.adapters.SendMessageListAdapter;
import com.example.chatdemoproject.bean.SendMessageModel;

import java.util.ArrayList;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class ChatFragment extends Fragment implements View.OnClickListener {
    private EditText message;
    private ImageView send_message, attachment;
    private SendMessageListAdapter adapter;
    public ArrayList<SendMessageModel> sendlist;
    private RecyclerView recyclerView;


    public ChatFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_fragment, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        message = (EditText) view.findViewById(R.id.message);
        send_message = (ImageView) view.findViewById(R.id.send_message);
        attachment = (ImageView) view.findViewById(R.id.attachment);
        recyclerView = (RecyclerView) view.findViewById(R.id.reyclerview_message_list);

        send_message.setOnClickListener(this);
        attachment.setOnClickListener(this);
        sendlist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SendMessageListAdapter(sendlist, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_message:
                sendMessage();
                break;
            case R.id.attachment:
                break;
        }

    }

    private void sendMessage() {
        String text = message.getText().toString();
        sendlist.add(new SendMessageModel(text, ""));
        adapter.notifyDataSetChanged();
        message.setText("");

    }
}
