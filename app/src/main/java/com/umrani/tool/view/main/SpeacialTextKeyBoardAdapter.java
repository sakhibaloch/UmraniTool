package com.umrani.tool.view.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*import com.testapp.ramboostergfxtool.R;
import com.testapp.ramboostergfxtool.util.SpecialKeyboardItemClick;*/
import com.umrani.tool.R;

public class SpeacialTextKeyBoardAdapter extends RecyclerView.Adapter<SpeacialTextKeyBoardAdapter.SpecialViewHolder> {
    public Context context;
    int position = 0;
    public SpecialKeyboardItemClick.KeyboardItemClick specialKeyboardItemClick;
    public String[] stringArrays = new String[0];

    public SpeacialTextKeyBoardAdapter(Context context2, SpecialKeyboardItemClick.KeyboardItemClick keyboardItemClick) {
        this.context = context2;
        this.specialKeyboardItemClick = keyboardItemClick;
    }

    @Override
    @NonNull
    public SpecialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SpecialViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_emoji, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull SpecialViewHolder specialViewHolder, int i) {
        String[] strArr = this.stringArrays;
        if (strArr.length >= 1) {
            final String str = strArr[i];
            specialViewHolder.textView.setTextSize(this.position == 5 ? 14.0f : 20.0f);
            specialViewHolder.textView.setText(str);
            specialViewHolder.textView.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    SpeacialTextKeyBoardAdapter.this.specialKeyboardItemClick.itemClick(str);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.stringArrays.length;
    }


    public class SpecialViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public SpecialViewHolder(@NonNull View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.emojiType);
        }
    }

    public void setData(String[] strArr, int i) {
        this.position = i;
        this.stringArrays = new String[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            this.stringArrays[i2] = strArr[i2];
        }
    }
}
