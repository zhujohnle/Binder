package com.android.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   IDataManager mDataManager;
   boolean isServiceConnected;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      bindService();
      setContentView(R.layout.activity_main);
      findViewById(R.id.bt_binder_test_add).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(mDataManager!=null){
               try {
                  mDataManager.addTransData(new TransData(new StringBuffer("happy days").toString()));
                  Toast.makeText(getBaseContext(),"远程数据导入成功",Toast.LENGTH_LONG).show();
               } catch (RemoteException e) {
                  e.printStackTrace();
               }
            }
         }
      });

      findViewById(R.id.bt_binder_test_search).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(mDataManager!=null){
               List<TransData> mListData = null;
               try {
                  mListData = mDataManager.getTransData();
               } catch (RemoteException e) {
                  e.printStackTrace();
               }
               Toast.makeText(getBaseContext(),"当前数据:"+mListData.toString(),Toast.LENGTH_LONG).show();
            }

         }
      });
   }



   private void bindService(){
      Intent intent = new Intent(this, RemoteService.class);
      intent.setAction("com.android.binder.test");
      bindService(intent, mServiceContentTion, Context.BIND_AUTO_CREATE);
   }

   private  ServiceConnection mServiceContentTion = new ServiceConnection() {
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
          isServiceConnected = true;
          mDataManager =  Stub.asInterface(service);

      }

      @Override
      public void onServiceDisconnected(ComponentName name) {
         isServiceConnected = false;
      }
   };

   @Override
   protected void onStart() {
      super.onStart();
      if (!isServiceConnected) {
         bindService();
      }
   }

   @Override
   protected void onStop() {
      super.onStop();
      if (isServiceConnected) {
         unbindService(mServiceContentTion);
      }
   }
}
