package com.android.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * <pre>
 *     author : zhujiule
 *     e-mail : zhujohnle@gmail.com
 *     time   : 2020/03/17
 *     desc   :
 * </pre>
 */
public  abstract class Stub extends Binder implements IDataManager {

   public static final int TRANSAVTION_GETDATA= IBinder.FIRST_CALL_TRANSACTION;
   public static final int TRANSAVTION_ADDDATA = IBinder.FIRST_CALL_TRANSACTION + 1;


   public Stub() {
      this.attachInterface(this, DESCRIPTOR);
   }

   public static IDataManager asInterface(IBinder binder) {
      if (binder == null)
         return null;
      IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
      if (iin != null && iin instanceof IDataManager)
         return (IDataManager) iin;
      return new Proxy(binder);
   }

   @Override
   public IBinder asBinder() {
      return this;
   }

   @Override
   protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
      switch (code) {

         case INTERFACE_TRANSACTION:
            reply.writeString(DESCRIPTOR);
            return true;

         case TRANSAVTION_GETDATA:
            data.enforceInterface(DESCRIPTOR);
            List<TransData> result = this.getTransData();
            reply.writeNoException();
            reply.writeTypedList(result);
            return true;

         case TRANSAVTION_ADDDATA:
            data.enforceInterface(DESCRIPTOR);
            TransData arg0 = null;
            if (data.readInt() != 0) {
               arg0 = TransData.CREATOR.createFromParcel(data);
            }
            this.addTransData(arg0);
            reply.writeNoException();
            return true;

      }
      return super.onTransact(code, data, reply, flags);
   }
}
