package com.umrani.tool.view.main;


import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class SpecialTextItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {


        @Override
        public SpecialTextItem createFromParcel(Parcel parcel) {
            return new SpecialTextItem(parcel);
        }

        @Override
        public SpecialTextItem[] newArray(int i) {
            return new SpecialTextItem[i];
        }
    };
    protected ContentValues contentValues = null;

    public int describeContents() {
        return 0;
    }

    public SpecialTextItem() {
    }

    public SpecialTextItem(Parcel parcel) {
        this.contentValues = (ContentValues) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override
    public SpecialTextItem clone() {
        SpecialTextItem specialTextItem = new SpecialTextItem();
        ContentValues contentValues = this.contentValues;
        if (contentValues != null) {
            specialTextItem.cont(new ContentValues(contentValues));
        }
        return specialTextItem;
    }

    public String conVal(String str) {
        ContentValues contentValues = this.contentValues;
        if (contentValues == null || !contentValues.containsKey(str)) {
            return null;
        }
        return this.contentValues.getAsString(str);
    }

    public void cont(ContentValues contentValues) {
        this.contentValues = contentValues;
    }

    public void gCont(String str, String str2) {
        if (this.contentValues == null) {
            this.contentValues = new ContentValues();
        }
        this.contentValues.put(str, str2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.contentValues, i);
    }
}
