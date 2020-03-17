package com.android.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : zhujiule
 *     e-mail : zhujohnle@gmail.com
 *     time   : 2020-03-17 21:56
 * </pre>
 */
public class TransData implements Parcelable {

   public TransData(String name){
      this.name = name;
   }
   protected TransData(Parcel in) {
      name = in.readString();
   }

   public static final Creator<TransData> CREATOR = new Creator<TransData>() {
      @Override
      public TransData createFromParcel(Parcel in) {
         return new TransData(in);
      }

      @Override
      public TransData[] newArray(int size) {
         return new TransData[size];
      }
   };

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   String name;

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {

      dest.writeString(name);
   }
   @Override
   public String toString() {
      return "Data{"+"name='" + name + '\'' +
            '}';
   }
}
