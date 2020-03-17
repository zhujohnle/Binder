package com.android.binder;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * <pre>
 *     author : zhujiule
 *     e-mail : zhujohnle@gmail.com
 *     time   : 2020-03-17 22:02
 *     desc   :
 * </pre>
 */
public interface IDataManager extends IInterface {

   String DESCRIPTOR = "com.android.binder.test";
   void addTransData(TransData mTransData) throws RemoteException;
   List<TransData> getTransData() throws RemoteException;
}
