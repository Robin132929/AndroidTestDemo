package com.robin.testdemo.io;

import android.os.Parcel;
import android.os.Parcelable;

public class ABean implements Parcelable {
    private  int index;
    private String name;

    public ABean() {
    }

    protected ABean(Parcel in) {
        index = in.readInt();
        name = in.readString();
    }

    public static final Creator<ABean> CREATOR = new Creator<ABean>() {
        @Override
        public ABean createFromParcel(Parcel in) {
            return new ABean(in);
        }

        @Override
        public ABean[] newArray(int size) {
            return new ABean[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(name);
    }
}
