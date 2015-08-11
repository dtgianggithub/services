package com.example.giangdam.boundserviceex1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Giang.Dam on 7/29/2015.
 */
public class LocalService extends Service {

    //Binder given to clients
    private final IBinder mBinder = new LocalBinder();


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder{
        LocalService getService(){
            return LocalService.this;
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    //public methods for clients
    public int Sum(int a, int b){
        return a+b;
    }

    //..you can create any public method for clients here
}
