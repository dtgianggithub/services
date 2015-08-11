package com.example.giangdam.boundserviceex2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

/**
 * Created by Giang.Dam on 7/29/2015.
 */
public class MessengerService extends Service {

    //Command to the service to display message
    static final int MSG_SAY_HELLO = 1;


    //Handler of incoming message from clients
   // final Messenger mMessenger = new Messenger(new IncomingHandler());

    class IncomingHandler extends Handler{

        public void  handleMessage(Message msg){
            switch (msg.what){
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    final Messenger mMessenger = new Messenger(new IncomingHandler());






    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"Binding ...",Toast.LENGTH_SHORT).show();
        return  mMessenger.getBinder();
    }
}
