package com.example.giangdam.boundserviceex2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    //Messenger for comunicating with service
    Messenger mService = null;

    //Flag to indicate when the service bound or unbound
    boolean mBound = false;

    Button btnSayHello;



    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSayHello = (Button)findViewById(R.id.btnSayHello);
        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mBound) return;

                // Create and send a message to the service, using a supported 'what' value
                Message msg = Message.obtain(null,MessengerService.MSG_SAY_HELLO,0,0);
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    protected void onStart() {
        super.onStart();

        //Bind to service
        bindService(new Intent(this,MessengerService.class),mConnection, Context.BIND_AUTO_CREATE);
        }

        protected void onStop() {
            super.onStop();
            if(mBound){
                unbindService(mConnection);
                mBound = false;
            }
        }


}
