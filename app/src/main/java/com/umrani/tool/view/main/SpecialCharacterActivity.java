package com.umrani.tool.view.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
/*import com.testapp.ramboostergfxtool.R;
import com.testapp.ramboostergfxtool.adapter.MyPagerAdapter;
import com.testapp.ramboostergfxtool.adapter.SpecialAdapter;
import com.testapp.ramboostergfxtool.ads.AdControlHelp;
import com.testapp.ramboostergfxtool.ads.InterstitialAdShow;
import com.testapp.ramboostergfxtool.util.MyEdittext;
import com.testapp.ramboostergfxtool.util.SpecialKeyboardItemClick;
import com.testapp.ramboostergfxtool.util.SpecialTextItem;
import com.testapp.ramboostergfxtool.util.SpecialTextUtil;*/
import com.umrani.tool.R;

import java.util.ArrayList;

public class SpecialCharacterActivity extends AppCompatActivity implements SpecialKeyboardItemClick.KeyboardItemClick {
    public Handler adsHandler;
    public MyEdittext editText;
    public ImageView imageDelete;
    public ImageView imageEmoji;
    public InputMethodManager inputMethodManager;
    public MyPagerAdapter myPagerAdapter;
    public RecyclerView recyclerView;
    public RelativeLayout relativeLayout;
    public SpecialAdapter specialAdapter;
    public SpecialAdapter.SpecialTextItemClick specialTextItemClick;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public Toolbar toolbar;
   // AdControlHelp adControlHelp;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_special_character);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycle_special_text);
        this.editText = (MyEdittext) findViewById(R.id.edit_special);
        this.editText.addTextChangedListener(new MyTextChangeListenner(this));
        this.specialTextItemClick = new MyItemClick(this);
        this.specialAdapter = new SpecialAdapter(this, SpecialTextUtil.m15269a(), this.specialTextItemClick);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.specialAdapter);
        this.imageEmoji = (ImageView) findViewById(R.id.image_emoji);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.speacial_keyboard);
        this.imageEmoji.setOnClickListener(new EmojiOpenClick(this));
        this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager_special_text);
        this.myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), this);
        this.viewPager.setAdapter(this.myPagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.relativeLayout.setVisibility(View.GONE);


        this.editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                SpecialCharacterActivity specialCharacterActivity = SpecialCharacterActivity.this;
                if (specialCharacterActivity.isRect(specialCharacterActivity.editText.getRootView())) {
                    SpecialCharacterActivity.this.relativeLayout.setVisibility(View.GONE);
                }
            }
        });
        this.imageDelete = (ImageView) findViewById(R.id.btnDelete);
        this.imageDelete.setOnClickListener(new DeleteClick(this));


    }

    @Override
    public void itemClick(String str) {
        String obj = this.editText.getText().toString();
        int currentPosition = this.editText.getCurrentPosition();
        if (currentPosition == 0) {
            MyEdittext myEdittext = this.editText;
            myEdittext.setText(str + obj);
            this.editText.setSelection(str.length());
        } else if (currentPosition == obj.length()) {
            MyEdittext myEdittext2 = this.editText;
            myEdittext2.setText(obj + str);
            this.editText.setSelection(obj.length() + str.length());
        } else {
            String substring = obj.substring(0, currentPosition);
            String substring2 = obj.substring(currentPosition);
            this.editText.setText(substring + str + substring2);
            this.editText.setSelection(obj.length());
        }
    }

    class MyItemClick implements SpecialAdapter.SpecialTextItemClick {
        final SpecialCharacterActivity specialCharacterActivity;

        MyItemClick(SpecialCharacterActivity specialCharacterActivity2) {
            this.specialCharacterActivity = specialCharacterActivity2;
        }

        @Override
        public void editClick(String str) {
            this.specialCharacterActivity.editText.setText(str);
        }

        @Override
        public void copyClick(String str) {
            ((ClipboardManager) SpecialCharacterActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Special Character", str));
            Toast.makeText(SpecialCharacterActivity.this, "Coppied", 0).show();
            Log.d("Special", str);
        }
    }

    class MyTextChangeListenner implements TextWatcher {
        final SpecialCharacterActivity specialCharacterActivity;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public MyTextChangeListenner(SpecialCharacterActivity specialCharacterActivity2) {
            this.specialCharacterActivity = specialCharacterActivity2;
        }

        public void afterTextChanged(Editable editable) {
            ArrayList<SpecialTextItem> arrayList;
            String trim = editable.toString().trim();
            if (trim.isEmpty()) {
                arrayList = SpecialTextUtil.m15269a();
            } else {
                arrayList = SpecialTextUtil.getStringCharacter(trim);
            }
            this.specialCharacterActivity.specialAdapter.setSpecialTextItemArrayList(arrayList);
        }
    }

    class EmojiOpenClick implements View.OnClickListener {
        final SpecialCharacterActivity specialCharacterActivity;

        public EmojiOpenClick(SpecialCharacterActivity specialCharacterActivity2) {
            this.specialCharacterActivity = specialCharacterActivity2;
        }
        @Override
        public void onClick(View view) {
            SpecialCharacterActivity.this.emojiOpen();
        }
    }

    public void emojiOpen() {
        if (isKeyBoardOpen()) {
            hideKeyBoard();
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (SpecialCharacterActivity.this.relativeLayout.getVisibility() == View.VISIBLE) {
                    SpecialCharacterActivity.this.relativeLayout.setVisibility(View.GONE);
                } else {
                    SpecialCharacterActivity.this.relativeLayout.setVisibility(View.VISIBLE);
                }
            }
        }, 200);
    }

    public boolean isRect(View view) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        return ((float) (view.getBottom() - rect.bottom)) > view.getResources().getDisplayMetrics().density * 100.0f;
    }

    public void hideKeyBoard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            this.inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public boolean isKeyBoardOpen() {
        return this.inputMethodManager.isAcceptingText();
    }

    @Override
    public void onBackPressed() {
        RelativeLayout relativeLayout2 = this.relativeLayout;
        if (relativeLayout2 == null || relativeLayout2.getVisibility() != 0) {
            super.onBackPressed();
        } else {
            this.relativeLayout.setVisibility(View.GONE);
        }
    }

    class DeleteClick implements View.OnClickListener {
        final SpecialCharacterActivity specialCharacterActivity;

        public DeleteClick(SpecialCharacterActivity specialCharacterActivity2) {
            this.specialCharacterActivity = specialCharacterActivity2;
        }
        @Override
        public void onClick(View view) {
            SpecialCharacterActivity.this.deleteSpecialCharacter();
        }
    }

    public void deleteSpecialCharacter() {
        int length = this.editText.getText().toString().length();
        int currentPosition = this.editText.getCurrentPosition();
        boolean z = false;
        boolean z2 = length < 1;
        if (currentPosition == 0) {
            z = true;
        }
        if (!z2 && !z) {
            int i = currentPosition - 1;
            MyEdittext myEdittext = this.editText;
            myEdittext.setText(new StringBuilder(myEdittext.getText().toString()).deleteCharAt(i).toString());
            this.editText.setSelection(i);
        }
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
