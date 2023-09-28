package com.umrani.tool.view.main;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.umrani.tool.R;

import java.util.ArrayList;
/*import com.testapp.ramboostergfxtool.R;
import com.testapp.ramboostergfxtool.util.SpecialTextItem;
import java.util.ArrayList;*/

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<SpecialTextItem> specialTextItemArrayList;
    public SpecialTextItemClick specialTextItemClick;

    public interface SpecialTextItemClick {
        void copyClick(String str);

        void editClick(String str);
    }

    public SpecialAdapter(Context context2, ArrayList<SpecialTextItem> arrayList, SpecialTextItemClick specialTextItemClick2) {
        this.specialTextItemArrayList = arrayList;
        this.context = context2;
        this.specialTextItemClick = specialTextItemClick2;
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.special_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(this.specialTextItemArrayList.get(i).conVal(AppMeasurementSdk.ConditionalUserProperty.NAME));
        myViewHolder.imageEdit.setOnClickListener(new EditClick(this, i));
        myViewHolder.imageCopy.setOnClickListener(new CoppyClick(this, i));
    }

    @Override
    public int getItemCount() {
        return this.specialTextItemArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCopy;
        ImageView imageEdit;
        TextView textView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.text_special_item);
            this.imageCopy = (ImageView) view.findViewById(R.id.image_coppy_special);
            this.imageEdit = (ImageView) view.findViewById(R.id.image_edit_special);
        }
    }

    public void setSpecialTextItemArrayList(ArrayList<SpecialTextItem> arrayList) {
        this.specialTextItemArrayList.clear();
        this.specialTextItemArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }


    public class EditClick implements View.OnClickListener {
        final int position;
        final SpecialAdapter specialAdapter;

        public EditClick(SpecialAdapter specialAdapter2, int i) {
            this.specialAdapter = specialAdapter2;
            this.position = i;
        }

        @Override
        public void onClick(View view) {
            this.specialAdapter.specialTextItemClick.editClick(SpecialAdapter.this.specialTextItemArrayList.get(this.position).conVal(AppMeasurementSdk.ConditionalUserProperty.NAME));
        }
    }


    public class CoppyClick implements View.OnClickListener {
        final int position;
        final SpecialAdapter specialAdapter;

        public CoppyClick(SpecialAdapter specialAdapter2, int i) {
            this.specialAdapter = specialAdapter2;
            this.position = i;
        }

        @Override
        public void onClick(View view) {
            this.specialAdapter.specialTextItemClick.copyClick(SpecialAdapter.this.specialTextItemArrayList.get(this.position).conVal(AppMeasurementSdk.ConditionalUserProperty.NAME));
        }
    }
}

