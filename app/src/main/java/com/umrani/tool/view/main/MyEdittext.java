package com.umrani.tool.view.main;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;

public class MyEdittext extends AppCompatEditText {
    int position = 0;

    public MyEdittext(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getCurrentPosition() {
        return this.position;
    }


    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        this.position = i;
    }
}
