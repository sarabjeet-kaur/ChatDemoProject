package com.example.chatdemoproject.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatdemoproject.R;
import com.example.chatdemoproject.service.MyFirebaseInstanceIDService;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

/**
 * Created by sarabjjeet on 9/29/17.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private Button btn_register, btn_chat;
    private ProgressDialog pd = null;

    boolean isRegister = false;
    String token;

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);


        initView(view);

        return view;
    }


    private void initView(View view) {
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_chat = (Button) view.findViewById(R.id.btn_chat);


        btn_register.setOnClickListener(this);
        btn_chat.setOnClickListener(this);

        pd = new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.setMessage(getString(R.string.please_wait));


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_chat:
                if (MyFirebaseInstanceIDService.refreshedToken!=null){
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    ChatFragment fragment = new ChatFragment();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please try again,not successfully register...", Toast.LENGTH_SHORT).show();
                }

        }
    }



    private void register() {
        if (isRegister == false) {

           /* if (pd != null && !pd.isShowing())
                pd.show();*/

            token = FirebaseInstanceId.getInstance().getToken();
            try{
                if (token != null) {
                    if (pd != null && pd.isShowing())
                        pd.dismiss();
                }
            }catch (Exception e){
                e.printStackTrace();
                Log.e("execption","-----");
            }

            btn_chat.setVisibility(View.VISIBLE);
            btn_register.setText("UnRegister");
            isRegister = true;

        } else {
            try {

                if (pd != null && !pd.isShowing())
                    pd.show();
                isRegister = false;
                btn_chat.setVisibility(View.GONE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    btn_register.setText("Register");
                                    if (pd != null && pd.isShowing())
                                        pd.dismiss();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("exception", ".....");
            }

        }
    }



}
