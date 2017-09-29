package com.example.chatdemoproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.chatdemoproject.fragment.RegisterFragment;
import com.example.chatdemoproject.service.MyFirebaseInstanceIDService;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    private FrameLayout conatiner;
    private MyBroadCastReceiver receiver = null;
    private IntentFilter filter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        conatiner=(FrameLayout)findViewById(R.id.container);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RegisterFragment fragment=new RegisterFragment();
        fragmentTransaction.add(R.id.container,fragment);
        //fragmentTransaction.addToBackStack(RegisterFragment.class.getSimpleName());
        fragmentTransaction.commit();


        receiver = new MyBroadCastReceiver();
        filter = new IntentFilter(MyFirebaseInstanceIDService.TOKEN_INTENT);


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
    }

    public class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e(TAG, "broad cast for token received");

           /* if (intent.getAction().equalsIgnoreCase(MyFirebaseInstanceIDService.TOKEN_INTENT)) {
                Log.e(TAG, "broad cast for token received");

                if (intent.hasExtra(MyFirebaseInstanceIDService.TOKEN)) {
                    Log.e(TAG, "token is "+intent.getStringExtra(MyFirebaseInstanceIDService.TOKEN));

                }
//                   String token = intent.getStringExtra(MyFirebaseInstanceIDService.TOKEN);
//
//                    if (pd != null && pd.isShowing())
//                        pd.dismiss();
//                }


            }*/
        }

    }


    public String getTopFragment(){

        return null;
    }
}
