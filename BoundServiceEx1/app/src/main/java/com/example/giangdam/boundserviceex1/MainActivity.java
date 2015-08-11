package com.example.giangdam.boundserviceex1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    //This example use IBindder class to execute BoundService

    EditText txtNumber1,txtNumber2, txtResult;
    Button btnSum;


    LocalService mService;
    boolean mBound = false;



    protected void onStart() {
        super.onStart();

        //Bind to LocalService
        Intent intent = new Intent(this,LocalService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);

    }

    protected void onStop() {
        super.onStop();
       if(mBound){
           unbindService(mConnection);
           mBound = false;
       }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber1 = (EditText)findViewById(R.id.txtNumber1);
        txtNumber2 = (EditText)findViewById(R.id.txtNumber2);
        txtResult = (EditText)findViewById(R.id.txtResult);
        btnSum = (Button)findViewById(R.id.btnSum);


        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    int result = mService.Sum(Integer.parseInt(txtNumber1.getText().toString()),Integer.parseInt(txtNumber2.getText().toString()));
                    txtResult.setText(String.valueOf(result));
                }
            }
        });
    }


    //Define callbacks for service binding, passed to bindService()
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            LocalService.LocalBinder binder = (LocalService.LocalBinder)service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };


}
