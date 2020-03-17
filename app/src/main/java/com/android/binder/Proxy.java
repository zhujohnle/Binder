package com.android.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * <pre>
 *     author : zhujiule
 *     e-mail : xxx@xx
 *     time   : 2020/03/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Proxy implements IDataManager {

   IBinder mBinder;

   public Proxy(IBinder mBinder){
      this.mBinder = mBinder;
   }

   @Override
   public void addTransData(TransData mTransData) throws RemoteException {
      Parcel data = Parcel.obtain();
      Parcel replay = Parcel.obtain();

      try {
         data.writeInterfaceToken(IDataManager.DESCRIPTOR);
         if (mTransData != null) {
            data.writeInt(1);
            mTransData.writeToParcel(data, 0);
         } else {
            data.writeInt(0);
         }
         mBinder.transact(Stub.TRANSAVTION_ADDDATA, data, replay, 0);
         replay.readException();
      } finally {
         replay.recycle();
         data.recycle();
      }
   }

   @Override
   public List<TransData> getTransData() throws RemoteException{
      Parcel data = Parcel.obtain();
      Parcel replay = Parcel.obtain();
      List<TransData> result;

      try {
         data.writeInterfaceToken(DESCRIPTOR);
         mBinder.transact(Stub.TRANSAVTION_GETDATA, data, replay, 0);
         replay.readException();
         result = replay.createTypedArrayList(TransData.CREATOR);
      } finally {
         replay.recycle();
         data.recycle();
      }
      return result;
   }

   @Override
   public IBinder asBinder() {
      return mBinder;
   }
}
