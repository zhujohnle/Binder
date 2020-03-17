package com.android.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth &{zhujiule}
 * @date 2020/3/17
 * @copyright
 **/
public class RemoteService extends Service {

   List<TransData> mDataList = new ArrayList<>();
   @Override
   public void onCreate() {
      super.onCreate();
   }


   @Override
   public IBinder onBind(Intent intent) {
      return mStub;
   }

   private Stub mStub = new Stub() {
      @Override
      public void addTransData(TransData mTransData) {
         mDataList.add(mTransData);
      }

      @Override
      public List<TransData> getTransData() {
         return mDataList;
      }
   };


}
