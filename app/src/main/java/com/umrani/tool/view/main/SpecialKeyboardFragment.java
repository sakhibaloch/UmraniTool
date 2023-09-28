package com.umrani.tool.view.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/*import com.testapp.ramboostergfxtool.R;
import com.testapp.ramboostergfxtool.adapter.SpeacialTextKeyBoardAdapter;
import com.testapp.ramboostergfxtool.util.SpecialKeyboardItemClick;*/
import com.umrani.tool.R;

public class SpecialKeyboardFragment extends Fragment {
    private static final String ARG_PARAM1 = "ARG_listData";
    private static final String ARG_PARAM2 = "ARG_POSITION";
    SpecialKeyboardItemClick.KeyboardItemClick keyboardItemClick;
    private String mParam1;
    private int mParam2;
    int position;
    RecyclerView recyclerView;
    SpeacialTextKeyBoardAdapter speacialTextKeyBoardAdapter;
    String[] stringArrays;

    public static SpecialKeyboardFragment newInstance(String[] strArr, int i) {
        SpecialKeyboardFragment specialKeyboardFragment = new SpecialKeyboardFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(ARG_PARAM1, strArr);
        bundle.putInt(ARG_PARAM2, i);
        specialKeyboardFragment.setArguments(bundle);
        return specialKeyboardFragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.stringArrays = getArguments().getStringArray(ARG_PARAM1);
            this.position = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_fragment_emoji, viewGroup, false);
        if (this.stringArrays == null) {
            return inflate;
        }
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recycleView);
        this.recyclerView.setHasFixedSize(true);
        if (this.position == 5) {
            this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        } else {
            this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        }
        this.speacialTextKeyBoardAdapter = new SpeacialTextKeyBoardAdapter(getContext(), this.keyboardItemClick);
        this.speacialTextKeyBoardAdapter.setData(this.stringArrays, this.position);
        this.recyclerView.setAdapter(this.speacialTextKeyBoardAdapter);
        return inflate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.keyboardItemClick = (SpecialKeyboardItemClick.KeyboardItemClick) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.keyboardItemClick = null;
    }
}

